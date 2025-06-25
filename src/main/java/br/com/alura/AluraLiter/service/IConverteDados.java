package br.com.alura.AluraLiter.service;

public interface IConverteDados {
    <T> T  obterDados(String json, Class<T> classe);
}
