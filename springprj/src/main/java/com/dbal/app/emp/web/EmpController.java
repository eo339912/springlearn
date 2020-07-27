package com.dbal.app.emp.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dbal.app.common.FileRenamePolicy;
import com.dbal.app.emp.EmpVO;
import com.dbal.app.emp.service.EmpService;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Controller //bean등록, dispatcher 서블릿이 인식할 수 있는 컨트롤러로 변환 //@component 상속
public class EmpController {
	@Autowired
	EmpService empService;
	
	@Autowired
	@Qualifier("dataSource")
	DataSource datasource;
	
//	@RequestMapping("/empList.do")
//	public String empList(Model model) {
//		model.addAttribute("empList", dao.empList());
//		return "emp/list";
//	}
	
	//등록폼
	@RequestMapping("insertFormEmp")
	public String insertFormEmp(EmpVO vo) {
		return "empty/emp/insertEmp";
	}
	
	//등록처리
	@RequestMapping("insertEmp")
	//@ModelAttribute("evo") 이름을 evo로 사용하겠다.
	public String insertEmp(@ModelAttribute("evo") EmpVO vo,  //1. 커멘드객체
								Model model,
								@RequestParam String firstName, // 2. = request.getParam("firstName")
								@RequestParam(required = false, defaultValue = "kim", value = "lastName") String ln,
								@RequestParam Map map
			) throws IOException{
		//첨부파일 처리
		MultipartFile file = vo.getUploadFile();
//		MultipartFile[] file = vo.getUploadFile();
//		for(MultipartFile file : file)
		String filename = "";
		if(file != null && file.getSize() > 0) {
			File upFile = FileRenamePolicy.rename(new File("d:/upload/", file.getOriginalFilename()));
			filename = upFile.getName();
			file.transferTo(upFile);
		}
		vo.setProfile(filename);
		//
		
		empService.insertEmp(vo);
		//System.out.println(vo.getMsg());
		//System.out.println(vo.getFirstName() + ":" + vo.getLastName());
		//System.out.println("parameter:" + firstName + ":" + ln);
		//System.out.println("map" + map.get("firstName") + ":"+ map.get("lastName"));
		//model.addAttribute("evo", vo);
		return "home";
	}
	
	//파일다운로드
	@RequestMapping("download")
	public ModelAndView download(@RequestParam String name) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("download");
		mv.addObject("downloadFile", new File("d:/upload/" , name));
		return mv;
	}
	
	
	//단건조회
	@RequestMapping("getEmp/{employeeId}") //getEmp?employeeId=aaa
	public String getEmp(@PathVariable String employeeId, Model model, EmpVO empVO) {
		empVO.setEmployeeId(employeeId);
		model.addAttribute("emp", empService.getEmp(empVO));
		return "empty/emp/getEmp";
	}
	
	//목록조회
	@RequestMapping("empList")
	public String empList(Model model, EmpVO empVO) {
		model.addAttribute("empList", empService.getEmpList(empVO));
		System.out.println("empList서비스 호출");
		return "emp/empList";
	}
	
	//emp관리
	@RequestMapping("empClient")
	public String empClient() {
		return "admin/emp/empClient";
	}
	
	//ajax 목록
	@RequestMapping("ajaxEmpList")
	public @ResponseBody List<EmpVO> ajaxEmpList(EmpVO empVO){
		return empService.getEmpList(empVO);
	}
	
	//수정폼
	//수정처리
	//삭제처리
	//차트 데이터
	@RequestMapping("chartDeptEmpCnt")
	public @ResponseBody List<Map<String, Object>> chartDeptEmpCnt(){
		return empService.getDeptEmpCnt();
	}
	
	@RequestMapping("report.do")
	public void report(HttpServletRequest request, HttpServletResponse response) throws Exception {
	Connection conn = datasource.getConnection();
	//InputStream jasperStream = getClass().getResourceAsStream("/reports/emp_list.jasper");
	//JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
	InputStream stream = getClass().getResourceAsStream("/reports/emp_list.jrxml");
	JasperReport jasperReport = JasperCompileManager.compileReport(stream);
	
	HashMap<String,Object> map = new HashMap<String,Object>();
	map.put("p_departmentId", request.getParameter("dept"));
		
	JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, conn);
	JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
	}
	
	@RequestMapping("report1.do")
	public String report3(HttpServletRequest request, HttpServletResponse response, Model model) throws
	Exception {
	model.addAttribute("filename", "/reports/emp_list.jasper");
	return "pdfView";
	}
	
	@RequestMapping("getEmpAjax")
	@ResponseBody
	public EmpVO getEmpAjax(EmpVO empVO) {
		return empService.getEmp(empVO);
	}

	
}
