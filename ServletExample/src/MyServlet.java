import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServlet extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Boolean valid = true;
		String num1 = request.getParameter("Number_1");
		String num2 = request.getParameter("Number_2");
		String num3 = request.getParameter("Number_3");
		String num4 = request.getParameter("Number_4");
		String num5 = request.getParameter("Number_5");
		String num6 = request.getParameter("Number_6");
		int Numbers[] = new int[6];
	
		try {
			Numbers[0] = Integer.parseInt(num1);
			Numbers[1] = Integer.parseInt(num2);
			Numbers[2] = Integer.parseInt(num3);
			Numbers[3] = Integer.parseInt(num4);
			Numbers[4] = Integer.parseInt(num5);
			Numbers[5] = Integer.parseInt(num6);
		} catch (NumberFormatException e) {
			
			out.println("<HTML>");
			out.println("<HEAD><TITLE>Invalid Input </TITLE></HEAD>");
			out.println("<BODY>");
			out.println("You left A Space Empty Please Return And Fill This Space");
			out.println("</BODY></HTML>");
		}
		
	for (int i = 0 ; i <Numbers.length; i++)
	{
		if(Numbers[i] < 1 || Numbers[i] > 47)
		{
		valid = false;
		}
		
	}	
		if(valid == false)
		{
		out.println("<HTML>");
		out.println("<HEAD><TITLE>Invalid Input </TITLE></HEAD>");
		out.println("<BODY>");
		out.println("Values Entered Are Not Valid(Must be between 1-47)");
		out.println("</BODY></HTML>");
		}
		else if(valid == true)
		{
		Arrays.sort(Numbers);
		out.println("<HTML>");
		out.println("<HEAD><TITLE>Lotto Numbers </TITLE></HEAD>");
		out.println("<BODY>");
		out.println("Numbers:" + Arrays.toString(Numbers));
		out.println("</BODY></HTML>");
		out.close();
		}
	}
}	
		
		
	
	



