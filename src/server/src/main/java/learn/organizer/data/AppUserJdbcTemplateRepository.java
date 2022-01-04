package learn.organizer.data;

import learn.organizer.data.Mappers.AppUserMapper;
import learn.organizer.models.AppUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;


@Repository
public class AppUserJdbcTemplateRepository implements AppUserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final AppUserMapper appUserMapper;

    public AppUserJdbcTemplateRepository(JdbcTemplate jdbcTemplate, AppUserMapper appUserMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.appUserMapper = appUserMapper;
    }

    @Override
    public List<AppUser> findAll() {
        // limit until we develop a paging solution
        final String sql = "select * from app_user limit 1000;";
        return jdbcTemplate.query(sql, appUserMapper);
    }

    @Override
    @Transactional
    public AppUser findById(int accountId) {

        final String sql = "select * "
                + "from app_user "
                + "where app_user_id = ?;";

        AppUser result = jdbcTemplate.query(sql, appUserMapper, accountId).stream()
                .findAny().orElse(null);

        return result;
    }

    @Override
    public AppUser findByUsername(String username) {
        final String sql = "select * "
                + "from app_user "
                + "where username = ?;";

        AppUser result = jdbcTemplate.query(sql, appUserMapper, username).stream()
                .findAny().orElse(null);

        return result;
    }

    @Override
    @Transactional
    public AppUser add(AppUser appUser) {

        final String sql = "insert into app_user (username, password_hash) values (?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, appUser.getUsername());
            ps.setString(2, appUser.getPassword());

            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        appUser.setAppUserId(keyHolder.getKey().intValue());
        return appUser;
    }

    @Override
    @Transactional
    public boolean update(AppUser appUser) {

        final String sql = "update app_user set "
                + "username = ?, "
                + "password_hash = ?, "
                + "disabled = ? "
                + "where app_user_id = ?";

        return jdbcTemplate.update(sql, appUser.getUsername(), appUser.getPassword(), !appUser.isEnabled(), appUser.getAppUserId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int appUserId) {
        jdbcTemplate.update("delete from app_user_role where app_user_id = ?", appUserId);
        return jdbcTemplate.update("delete from app_user where app_user_id = ?", appUserId) > 0;
    }


}
