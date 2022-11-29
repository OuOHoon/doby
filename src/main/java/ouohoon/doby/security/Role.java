package ouohoon.doby.security;

public enum Role {
    ADMIN("ADMIN"), // 관리자 권한
    USER("USER"), // 일반 권한
    GUEST("GUEST"); // 손님 권한

    private String role;

    Role(String role) {
        this.role = role;
    }
}
