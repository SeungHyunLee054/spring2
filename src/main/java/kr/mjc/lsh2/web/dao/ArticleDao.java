package kr.mjc.lsh2.web.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * NamedParameterJdbcTemplate을 사용해서 ArticleDao 구현
 *
 * @author Jacob
 */
@Repository
@Slf4j
public class ArticleDao {

    String LIST_ARTICLES = """
      select articleId, title, userId, name, cdate, udate from article
      order by articleId desc limit ?,?""";

    String GET_ARTICLE = """
      select articleId, title, content, userId, name, cdate, udate from article
      where articleId=?""";

    String ADD_ARTICLE =
            "insert article(title, content, userId, name) values (:title, :content, :userId, :name)";

    String UPDATE_ARTICLE =
            "update article set title=:title, content=:content where articleId=:articleId and userId=:userId";

    String DELETE_ARTICLE = "delete from article where articleId=? and userId=?";

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * resultSet을 article object로 매핑하는 매퍼
     */
    private final RowMapper<Article> articleRowMapper =
            new BeanPropertyRowMapper<>(Article.class);

    /**
     * namedParameterJdbcTemplate을 주입하는 컨스트럭터
     */
    public ArticleDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
        log.info("{} constructed", getClass().getSimpleName());
    }

    /**
     * 게시글 목록
     */
    public List<Article> listArticles(int count, int page) {
        int offset = (page - 1) * count;
        return jdbcTemplate.query(LIST_ARTICLES, articleRowMapper, offset, count);
    }

    /**
     * 게시글 한 건 조회
     */
    public Article getArticle(int articleId) {
        return jdbcTemplate.queryForObject(GET_ARTICLE, articleRowMapper,
                articleId);
    }

    /**
     * 게시글 등록
     */
    public void addArticle(Article article) {
        namedParameterJdbcTemplate.update(ADD_ARTICLE,
                new BeanPropertySqlParameterSource(article));
    }

    /**
     * 게시글 수정
     */
    public int updateArticle(Article article) {
        return namedParameterJdbcTemplate.update(UPDATE_ARTICLE,
                new BeanPropertySqlParameterSource(article));
    }

    /**
     * 게시글 삭제
     */
    public int deleteArticle(int articleId, int userId) {
        return jdbcTemplate.update(DELETE_ARTICLE, articleId, userId);
    }
}