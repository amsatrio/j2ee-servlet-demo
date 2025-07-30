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

import io.github.amsatrio.dto.HelloWorldDto;
import io.github.amsatrio.dto.ResponseDto;
import io.github.amsatrio.service.HelloWorldService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/api/hello")
@RequestScoped
public class HelloWorldServletApi extends HttpServlet {

    @Inject
    private HelloWorldService helloWorldService;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        log.info("doGet()");

        ResponseDto<Object> responseDto = new ResponseDto<>(HttpServletResponse.SC_OK, "success",
                helloWorldService.find());

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().append(responseDto.toJsonString());
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HelloWorldDto helloWorldDto = null;

        ObjectMapper objectMapper = new ObjectMapper();
        helloWorldDto = objectMapper.readValue(request.getInputStream(), HelloWorldDto.class);

        helloWorldDto = helloWorldService.update(helloWorldDto.getMessage());

        ResponseDto<Object> responseDto = new ResponseDto<>(HttpServletResponse.SC_OK, "success",
                helloWorldDto);

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().append(responseDto.toJsonString());
    }
}