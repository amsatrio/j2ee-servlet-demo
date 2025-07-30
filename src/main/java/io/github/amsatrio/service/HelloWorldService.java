package io.github.amsatrio.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import io.github.amsatrio.dao.HelloWorldRepository;
import io.github.amsatrio.dto.HelloWorldDto;

@RequestScoped
public class HelloWorldService {
    @Inject
    private HelloWorldRepository helloWorldRepository;

    public HelloWorldDto find() {
        return helloWorldRepository.find();
    }

    public HelloWorldDto update(String data) {
        HelloWorldDto helloWorldDto = find();
        helloWorldDto.setMessage(data);
        return helloWorldRepository.update(helloWorldDto);
    }
}
