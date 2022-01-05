package learn.organizer.data;

import learn.organizer.data.Mappers.AppUserMapper;
import learn.organizer.models.AppUser;
import learn.organizer.models.Contact;
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
    private final ContactRepository contactRepository;

    public AppUserJdbcTemplateRepository(JdbcTemplate jdbcTemplate, AppUserMapper appUserMapper, ContactRepository contactRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.appUserMapper = appUserMapper;
        this.contactRepository = contactRepository;
    }

    @Override
    public List<AppUser> findAll() {
        // limit until we develop a paging solution
        final String sql = "select * from user limit 1000;";
        return jdbcTemplate.query(sql, appUserMapper);
    }

    @Override
    @Transactional
    public AppUser findById(int accountId) {

        final String sql = "select * "
                + "from user "
                + "where userId = ?;";

        AppUser result = jdbcTemplate.query(sql, appUserMapper, accountId).stream()
                .findAny().orElse(null);

        return result;
    }

    @Override
    public AppUser findByUsername(String username) {
        final String sql = "select * "
                + "from user "
                + "where username = ?;";

        AppUser result = jdbcTemplate.query(sql, appUserMapper, username).stream()
                .findAny().orElse(null);

        return result;
    }

    @Override
    @Transactional
    public AppUser add(AppUser appUser) {
        //System.out.println("called add");
        final String sql = "insert into user (username, password, userRole) values (?,?,?);";

        System.out.println(appUser.getRole());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, appUser.getUsername());
            ps.setString(2, appUser.getPassword());
            ps.setString(3, appUser.getRole());
            //System.out.println("inside");
            return ps;
        }, keyHolder);
        //System.out.println(rowsAffected);
        if (rowsAffected <= 0) {
            //System.out.println("Nada");
            return null;
        }
        //System.out.println("contact time");
        appUser.setAppUserId(keyHolder.getKey().intValue());

        //update contact userId with newly generated id for app user
        Contact contact=appUser.getContact();
        contact.setUserId(appUser.getAppUserId());
        contactRepository.addContact(contact);

        return appUser;
    }

    @Override
    @Transactional
    public boolean update(AppUser appUser) {

        final String sql = "update user set "
                + "username = ?, "
                + "password = ?, "
                + "role = ? "
                + "where userId = ?";

        return jdbcTemplate.update(sql, appUser.getUsername(), appUser.getPassword(), appUser.getRole(), appUser.getAppUserId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int appUserId) {
        //delete anything associated with user
        //activity they created
        //contacts
        //user itself
        return true;
    }


}
