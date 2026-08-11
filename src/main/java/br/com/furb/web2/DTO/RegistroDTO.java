package br.com.furb.web2.DTO;

import br.com.furb.web2.Entities.UserRoles;

public record RegistroDTO(String login, String password, UserRoles role) {
}
