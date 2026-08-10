package br.com.furb.web2.Entities;

public enum UserRoles {
    ADMIN("admin"),
    EMPREGADO("empregado");

  private String role;

    UserRoles(String role) {
        this.role = role;
    }
    public String getRole() {
        return role;
    }
}
