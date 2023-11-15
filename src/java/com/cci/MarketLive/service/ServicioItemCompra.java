package com.cci.MarketLive.service;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ServicioItemCompra extends Conexion implements ICrud<ItemCompraTO> {

    PreparedStatement stmt;
    ResultSet rs;
    List<ItemCompraTO> itemcompra;

    @Override
    public boolean create(ItemCompraTO objeto) throws SQLException {
        try {

            String query = "INSERT INTO item_compra (id, compra_id, item_id) VALUES (?, ?, ?)";
            stmt = super.getConexion().prepareStatement(query);
            stmt.setInt(1, objeto.getId());
            stmt.setInt(2, objeto.getIdcompra());
            stmt.setInt(3, objeto.getIditem());

            stmt.execute();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    @Override
    public ItemCompraTO read(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(ItemCompraTO objeto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ItemCompraTO> readAll() throws SQLException {
        itemcompra = new ArrayList<ItemCompraTO>();

        try {
            stmt = super.getConexion().prepareStatement("SELECT * FROM item_compra");
            rs = stmt.executeQuery();

            while (rs.next()) {
                ItemCompraTO itemCompraTO = new ItemCompraTO();

                int id = rs.getInt("id");
                int idcompra = rs.getInt("id_compra");
                int iditem = rs.getInt("id_item");

                itemCompraTO.setId(id);
                itemCompraTO.setIdcompra(idcompra);
                itemCompraTO.setIditem(iditem);

                itemCompraTO.add(itemCompraTO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null && rs != null) {
                rs.close();
                stmt.close();
            }
        }

        return itemcompra;
    }
}


