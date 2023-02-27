package kr.mjc.lsh2.web.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * NamedParameterJdbcTemplate을 사용해서 UserDao 구현
 *
 * @author Jacob
 */
@Repository
@Slf4j
public class UserDao {

    private static final String LIST_USERS =
            "select userId, email, name from user order by userId desc limit ?,?";

    private static final String GET_USER =
            "select userId, email, name from user where userId=?";

    private static final String LOGIN =
            "select userId, email, name from user where email=? and password=sha2(?,256)";

    private static final String ADD_USER =
            "insert user(email, password, name) values(:email, sha2(:password,256), :name)";

    private static final String UPDATE_PASSWORD =
            "update user set password=sha2(?,256) where userId=? and password=sha2(?,256)";

    private static final String DELETE_USER =
            "delete from user where userId=? and password=sha2(?,256)";

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * resultSet을 user object로 매핑하는 매퍼
     */
    private final RowMapper<User> userRowMapper =
            new BeanPropertyRowMapper<>(User.class);

    /**
     * namedParameterJdbcTemplate을 주입하는 컨스트럭터
     */
    public UserDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
        log.info("{} constructed", getClass().getSimpleName());
    }

    /**
     * 회원 목록
     *
     * @param count 목록의 갯수
     * @param page  페이지
     * @return 회원 목록
     */
    public List<User> listUsers(int count, int page) {
        int offset = (page - 1) * count;  // 목록의 시작 시점
        return jdbcTemplate.query(LIST_USERS, userRowMapper, offset, count);
    }

    /**
     * 회원정보 조회
     *
     * @param userId 회원번호
     * @return 회원 정보
     */
    public User getUser(int userId) {
        return jdbcTemplate.queryForObject(GET_USER, userRowMapper, userId);
    }

    /**
     * 로그인
     *
     * @param email    이메일
     * @param password 비밀번호
     * @return 로그인 성공하면 회원정보, 실패하면 NULL
     */
    public User login(String email, String password) {
        return jdbcTemplate.queryForObject(LOGIN, userRowMapper, email, password);
    }

    /**
     * 회원 가입
     *
     * @param user 회원정보
     * @throws DataAccessException 이메일 중복으로 회원가입 실패 시
     */
    public void addUser(User user) throws DataAccessException {
        SqlParameterSource params = new BeanPropertySqlParameterSource(user);
        namedParameterJdbcTemplate.update(ADD_USER, params);
    }

    /**
     * 비밀번호 수정
     *
     * @param userId          회원번호
     * @param currentPassword 현재 비밀번호
     * @param newPassword     새 비밀번호
     * @return 수정 성공시 1, 회원이 없거나 비밀번호가 틀리면 0
     */
    public int updatePassword(int userId, String currentPassword,
                              String newPassword) {
        return jdbcTemplate.update(UPDATE_PASSWORD, newPassword, userId,
                currentPassword);
    }

    /**
     * 회원 삭제
     *
     * @param userId   회원번호
     * @param password 비밀번호
     * @return 삭제 성공시 1, 회원번호가 없거나 비밀번호가 틀리면 0
     * @throws DataAccessException 게시글이 있는 회원을 삭제하려고 한 경우
     */
    public int deleteUser(int userId, String password)
            throws DataAccessException {
        return jdbcTemplate.update(DELETE_USER, userId, password);
    }
}
