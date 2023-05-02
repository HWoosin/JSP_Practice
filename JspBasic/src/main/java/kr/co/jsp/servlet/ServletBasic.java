package kr.co.jsp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 서블릿이란 웹 페이지를 자바 언어로만 구성하는 것.
 * 즉, jsp 파일을 자바 언어로만 구성하는 것입니다. (...con.jsp)
 * 톰캣을 사용해서 jsp 파일을 자동을 class로 변환 했다면
 * 이제는 직접 클래스를 생성해서 클래스로 요청을 처리해 보자는 것
 */
public class ServletBasic extends HttpServlet{
	
	//# 서블릿 클래스를 만드는 방법
	//1. HttpServlet 클래스를 상속.
	
	//2. 생성자를 선언 (선택)
	public ServletBasic() {
		System.out.println("페이지에 요청이 들어옴!");
		System.out.println("서블릿 객체가 생성됨!!!");
	}
	//3. HttpServlet이 제공하는 상속 메서드를 목적에 맞게 오버라이딩(재정의)한다.
	//클라이언트가 어떤 요청을 했을때 서버는 어떠한 처리와 함께 응답을 할 것인가?
	//init(), doGet, doPost(), service, destrop()...
	
	@Override
	public void init() throws ServletException {
		/*
		 * 페이지 요청이 들어왔을 때 처음 실행할 로직을 작성.
		 * init()은 컨테이너(서버 프로그램)에 의해 서블릿 객체가 생성될 때 최초 1회 자동으로 호출.
		 * 객체의 생성자와 비슷한 역할을 수행.
		 */
		System.out.println("init 메서드 호출");
	}
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 페이지 요청이 들어왔을때 자동으로 호출.
		 * get, post 구분 없이 들어오는 모든 요청과 응답을 관제할 수 있다.
		 * 매개값으로 요청, 응답 내장 객체가 전달
		 */
		
		//요청 방식이 뭐니?
		String method = request.getMethod();
		//요청 URI가 뭐니?
		String uri = request.getRequestURI();
		//요청 파라미터
		String queryString = request.getQueryString();
		
		//요청에 관련된 헤더 정보 읽기
		String cc = request.getHeader("Cache-Control");
		
		System.out.println("----- 값 확인하기 -----");
		System.out.println("method:" + method);
		System.out.println("uri:" + uri);
		System.out.println("queryString: "+queryString);
		System.out.println("cc: "+cc);
		
		//응답 화면 제작
		//클래스에서 브라우저로 바로 응답을 구현하기 위해서
		//PrinterWriter 객체를 사용함.
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter w = response.getWriter();
		
		String htmlCode = "";
		
		htmlCode += "<!DOCTYPE html>\r\n"
                + "<html>\r\n"
                + "<head>\r\n"
                + "<meta charset=\"UTF-8\">\r\n"
                + "<title>Insert title here</title>\r\n"
                + "</head>\r\n"
                + "<body>\r\n"
                + "\r\n"
                + "\t아이디: " + request.getParameter("id") + "\r\n"
                + "\t이메일: " + request.getParameter("email") + "\r\n"
                + "\r\n"
                + "</body>\r\n"
                + "</html>\r\n"
                + "";
		w.write(htmlCode); //버퍼에 응답하고자 하는 데이터 작성하기
		w.flush(); //버퍼를 비우면서 작성한 내용을 브라우저로 밀어내기
		w.close(); //객체 해제
		
	}
	
}







