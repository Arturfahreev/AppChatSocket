package servlets;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.net.*;

public class ReverseServlet extends HttpServlet{

    public void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int contentLength = req.getContentLength();
        byte[] bytes = new byte[contentLength];
        ServletInputStream inputStream = req.getInputStream();
        int c = 0;
        int count = 0;
        while ((c = inputStream.read(bytes, count, bytes.length - count)) != -1) {
            count += c;
        }
        inputStream.close();

        String str = new String(bytes);
        String decode = URLDecoder.decode(str, "UTF-8");
        String result = new StringBuffer((decode)).reverse().toString();
        resp.setStatus(HttpServletResponse.SC_OK);
        OutputStreamWriter writer = new OutputStreamWriter(resp.getOutputStream());
        writer.write(result);
        writer.flush();
        writer.close();
    }

}
