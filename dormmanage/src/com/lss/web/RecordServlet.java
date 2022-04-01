package com.lss.web;

import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lss.dao.DormBuildDao;
import com.lss.dao.RecordDao;
import com.lss.dao.StudentDao;
import com.lss.model.DormManager;
import com.lss.model.Record;
import com.lss.model.Student;
import com.lss.util.DbUtil;
import com.lss.util.StringUtil;

public class RecordServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DbUtil dbUtil = new DbUtil();
	RecordDao recordDao = new RecordDao();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		Object currentUserType = session.getAttribute("currentUserType");
		String s_studentText = request.getParameter("s_studentText");
		String dormBuildId = request.getParameter("buildToSelect");
		String searchType = request.getParameter("searchType");
		String action = request.getParameter("action");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		
		Record record = new Record();
		if("preSave".equals(action)) {
			recordPreSave(request, response);
			return;
		} else if("save".equals(action)){
			recordSave(request, response);
			return;
		} else if("delete".equals(action)){
			recordDelete(request, response);
			return;
		} else if("list".equals(action)) {
			if(StringUtil.isNotEmpty(s_studentText)) {
				if("name".equals(searchType)) {
					record.setStudentName(s_studentText);
				} else if("number".equals(searchType)) {
					record.setStudentNumber(s_studentText);
				} else if("dorm".equals(searchType)) {
					record.setDormName(s_studentText);
				}
			}
			if(StringUtil.isNotEmpty(dormBuildId)) {
				record.setDormBuildId(Integer.parseInt(dormBuildId));
			}
			session.removeAttribute("s_studentText");
			session.removeAttribute("searchType");
			session.removeAttribute("buildToSelect");
			request.setAttribute("s_studentText", s_studentText);
			request.setAttribute("searchType", searchType);
			request.setAttribute("buildToSelect", dormBuildId);
		} else if("search".equals(action)){
			if(StringUtil.isNotEmpty(s_studentText)) {
				if("name".equals(searchType)) {
					record.setStudentName(s_studentText);
				} else if("number".equals(searchType)) {
					record.setStudentNumber(s_studentText);
				} else if("dorm".equals(searchType)) {
					record.setDormName(s_studentText);
				}
				session.setAttribute("s_studentText", s_studentText);
				session.setAttribute("searchType", searchType);
			} else {
				session.removeAttribute("s_studentText");
				session.removeAttribute("searchType");
			}
			if(StringUtil.isNotEmpty(startDate)) {
				record.setStartDate(startDate);
				session.setAttribute("startDate", startDate);
			} else {
				session.removeAttribute("startDate");
			}
			if(StringUtil.isNotEmpty(endDate)) {
				record.setEndDate(endDate);
				session.setAttribute("endDate", endDate);
			} else {
				session.removeAttribute("endDate");
			}
			if(StringUtil.isNotEmpty(dormBuildId)) {
				record.setDormBuildId(Integer.parseInt(dormBuildId));
				session.setAttribute("buildToSelect", dormBuildId);
			}else {
				session.removeAttribute("buildToSelect");
			}
		} 
		Connection con = null;
		try {
			con=dbUtil.getCon();
			if("admin".equals((String)currentUserType)) {
				List<Record> recordList = recordDao.recordList(con, record);
				request.setAttribute("dormBuildList", recordDao.dormBuildList(con));
				request.setAttribute("recordList", recordList);
				request.setAttribute("mainPage", "admin/record.jsp");
				request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
			} else if("dormManager".equals((String)currentUserType)) {
				DormManager manager = (DormManager)(session.getAttribute("currentUser"));
				int buildId = manager.getDormBuildId();
				String buildName = DormBuildDao.dormBuildName(con, buildId);
				List<Record> recordList = recordDao.recordListWithBuild(con, record, buildId);
				request.setAttribute("dormBuildName", buildName);
				request.setAttribute("recordList", recordList);
				request.setAttribute("mainPage", "dormManager/record.jsp");
				request.getRequestDispatcher("mainManager.jsp").forward(request, response);
			} else if("student".equals((String)currentUserType)) {
				Student student = (Student)(session.getAttribute("currentUser"));
				List<Record> recordList = recordDao.recordListWithNumber(con, record, student.getStuNumber());
				request.setAttribute("recordList", recordList);
				request.setAttribute("mainPage", "student/record.jsp");
				request.getRequestDispatcher("mainStudent.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void recordDelete(HttpServletRequest request,
			HttpServletResponse response) {
		String recordId = request.getParameter("recordId");
		Connection con = null;
		try {
			con = dbUtil.getCon();
			recordDao.recordDelete(con, recordId);
			request.getRequestDispatcher("record?action=list").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void recordSave(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		String recordId = request.getParameter("recordId");
		String studentNumber = request.getParameter("studentNumber");
		String date = request.getParameter("date");
		String detail = request.getParameter("detail");
		Record record = new Record(studentNumber, date, detail); 
		if(StringUtil.isNotEmpty(recordId)) {
			if(Integer.parseInt(recordId)!=0) {
				record.setRecordId(Integer.parseInt(recordId));
			}
		}
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int saveNum = 0;
			HttpSession session = request.getSession();
			DormManager manager = (DormManager)(session.getAttribute("currentUser"));
			int buildId = manager.getDormBuildId();
			Student student = StudentDao.getNameById(con, studentNumber, buildId);
			if(student.getName() == null) {
				request.setAttribute("record", record);
				request.setAttribute("error", "用户名不能为空！");
				request.setAttribute("mainPage", "dormManager/recordSave.jsp");
				request.getRequestDispatcher("mainManager.jsp").forward(request, response);
			} else {
				record.setDormBuildId(student.getDormBuildId());
				record.setStudentName(student.getName());
				record.setDormName(student.getDormName());
				if(StringUtil.isNotEmpty(recordId) && Integer.parseInt(recordId)!=0) {
					saveNum = recordDao.recordUpdate(con, record);
				} else {
					saveNum = recordDao.recordAdd(con, record);
				}
				if(saveNum > 0) {
					request.getRequestDispatcher("record?action=list").forward(request, response);
				} else {
					request.setAttribute("record", record);
					request.setAttribute("error", "错误提示！");
					request.setAttribute("mainPage", "dormManager/recordSave.jsp");
					request.getRequestDispatcher("mainManager.jsp").forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void recordPreSave(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		String recordId = request.getParameter("recordId");
		String studentNumber = request.getParameter("studentNumber");
		Connection con = null;
		try {
			con = dbUtil.getCon();
			if (StringUtil.isNotEmpty(recordId)) {
				Record record = recordDao.recordShow(con, recordId);
				request.setAttribute("record", record);
			} else {
				Calendar rightNow = Calendar.getInstance();       
				SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");   
				String sysDatetime = fmt.format(rightNow.getTime());
				request.setAttribute("studentNumber", studentNumber);
				request.setAttribute("date", sysDatetime);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("mainPage", "dormManager/recordSave.jsp");
		request.getRequestDispatcher("mainManager.jsp").forward(request, response);
	}
}
