package io.github.amsatrio.servlet;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.amsatrio.dto.ResponseDto;
import io.github.amsatrio.service.HelloWorldService;

@WebServlet("/web/hello")
@RequestScoped
public class HelloWorldServletWeb extends HttpServlet {

    @Inject
    private HelloWorldService helloWorldService;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        ResponseDto<Object> responseDto = new ResponseDto<>(HttpServletResponse.SC_OK, "success",
                helloWorldService.find());

        String userName = "Guest";
        if (request.getParameter("name") != null) {
            userName = request.getParameter("name");
        }
        request.setAttribute("userName", userName);

        ObjectMapper objectMapper = new ObjectMapper();
        request.setAttribute("responseDto", objectMapper.writeValueAsString(responseDto));
        response.setStatus(HttpServletResponse.SC_OK);
        request.getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(request, response);
    }
}