<%@page import="com.myweb.board.commons.PageVO"%>
<%@page import="com.myweb.board.model.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<%--
		for(int i=1; i<=300; i++){
			String writer = "���׽�Ʈ" + i;
			String title = "�׽�Ʈ �Դϴ�." + i;
			String content = "�׽�Ʈ ���̴ϱ� ������ �ϼ���!" + i;
			BoardDAO.getInstance().regist(writer, title, content);
		}
	--%>
	
	<%
		int countArticles = BoardDAO.getInstance().countArticels();
		out.print("# �� �Խù� �� : " + countArticles + "�� <br>");
		out.print("-----------------------------------------<br>");
		
		//�� ������ ��ȣ ��� �׽�Ʈ
		PageVO paging = new PageVO();
		paging.setPage(12);//����ڰ� �����ִ� ������
		paging.setCpp(20);//��ȭ�鿡 ������ �Խñ� ��
		int displayBtn = 10; // ��������ư ��
		
		int endPage = (int)Math.ceil(paging.getPage() / (double)displayBtn)* displayBtn;
		out.print("�� ������ ��ȣ : "+endPage+"��<br>");
		
		//���������� ��ȣ ��� �׽�Ʈ
		int beginPage = (endPage - displayBtn) + 1;
		out.print("���� ������ ��ȣ : "+beginPage+"��<br>");
		
		//������ư Ȱ��ȭ, ��Ȱ��ȭ ����
		boolean isPrev = (beginPage == 1) ? false : true;
		out.print("���� ��ư Ȱ��ȭ ���� : "+isPrev+"<br>");
		
		//������ư Ȱ��ȭ, ��Ȱ��ȭ ����
		boolean isNext = (countArticles <= (endPage * paging.getCpp())) ? false : true;
		out.print("���� ��ư Ȱ��ȭ ���� : "+isNext+"<br>");
		
		//�� ������ ����
		if(!isNext){ //isNext == false
			endPage = (int)Math.ceil(countArticles / (double)paging.getCpp());
		}
		out.print("���� �� �� ������ ��ȣ : "+endPage+"��<br>");
	%>
	

</body>
</html>












