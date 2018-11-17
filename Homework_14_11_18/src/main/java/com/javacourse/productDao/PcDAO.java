package com.javacourse.productDao;

import com.javacourse.dbInterction.DatabaseConnectionPoolResource;
import com.javacourse.productModels.Pc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static com.javacourse.App.logger;

public class PcDAO extends AbstractDAO<Integer, Pc> {
    @Override
    public Pc findById(Integer id) {
        Pc resultingItem = null;
        try(Connection con= DatabaseConnectionPoolResource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * from pc where code=?")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                resultingItem = constructItem(rs);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return resultingItem;
    }

    @Override
    public List<Pc> findAll() {
        List<Pc> resultingItems = new LinkedList<>();
        try(Connection con=DatabaseConnectionPoolResource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM pc order by price ASC;")){
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                resultingItems.add(constructItem(rs));
            }
        }catch (SQLException e){
            logger.error(e.getMessage());
        }
        return resultingItems;
    }

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(Pc entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pc update(Pc entity) {
        throw new UnsupportedOperationException();
    }

    private Pc constructItem(ResultSet rs) throws SQLException {
        Pc resultingItem = new Pc();
        resultingItem.setCode(rs.getInt(1));
        resultingItem.setModel(rs.getString(2));
        resultingItem.setSpeed(rs.getByte(3));
        resultingItem.setRam(rs.getByte(4));
        resultingItem.setHd(rs.getInt(5));
        resultingItem.setCd(rs.getString(6));
        resultingItem.setPrice(rs.getBigDecimal(7));
        return resultingItem;
    }

}
