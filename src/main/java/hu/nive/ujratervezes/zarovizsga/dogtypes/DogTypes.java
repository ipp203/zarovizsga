package hu.nive.ujratervezes.zarovizsga.dogtypes;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class DogTypes {
    private final DataSource dataSource;

    public DogTypes(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<String> getDogsByCountry(String country) {

        String upperCasedCountry = country.toUpperCase();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT name FROM dog_types WHERE country=?")) {
            pstmt.setString(1, upperCasedCountry);

            return getDogTypesByPreparedStatement(pstmt);

        } catch (SQLException sqle) {
            throw new IllegalStateException("Can not connect to dog_types table", sqle);
        }
    }

    private List<String> getDogTypesByPreparedStatement(PreparedStatement pstmt) {
        List<String> types = new ArrayList<>();
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                types.add(rs.getString("name").toLowerCase());
            }
            types.sort(Comparator.naturalOrder());
            return types;
        } catch (SQLException sqle) {
            throw new IllegalStateException("Can not select from dog_types table", sqle);
        }
    }
}
