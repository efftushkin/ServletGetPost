package com.efftushkin.app.ServletGetPost;

import com.efftushkin.app.GetPow.GetPow;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Enumeration;

public class ServletGetPost extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (ServletOutputStream servletOutputStream = resp.getOutputStream()) {
            Enumeration<String> parameterNames = req.getParameterNames();

            StringBuilder request = new StringBuilder("Hello GET");

            BigDecimal number = null;
            BigDecimal exponent = null;

            while (parameterNames.hasMoreElements()) {
                String parameterName = parameterNames.nextElement();
                String parameter = req.getParameter(parameterName);

                request.append(", " + parameterName + " = " + parameter);

                if (parameterName.equalsIgnoreCase("number")) {
                    try {
                        number = new BigDecimal(parameter);
                    } catch (Throwable e) {
                        number = null;
                    }
                }
                if (parameterName.equalsIgnoreCase("exponent")) {
                    try {
                        exponent = new BigDecimal(parameter);
                    } catch (Throwable e) {
                        exponent = null;
                    }
                }

            }
            if (number != null && exponent != null) {
                servletOutputStream.print("<html>" + number.doubleValue() + "<sup>" + exponent.intValue() + "</sup> = ");
                servletOutputStream.print(GetPow.getPow(number.doubleValue(), exponent.intValue()) + "</html>");
            } else {
                servletOutputStream.print(request.toString());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (ServletOutputStream servletOutputStream = resp.getOutputStream()) {
            servletOutputStream.print("Hello POST");
        }
    }
}
