package com.example.demo.dao.impl;

import com.example.demo.dao.AvocatDao;
import com.example.demo.entities.Avocat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AvocatDaoImp implements AvocatDao {
    private Connection conn= DB.getConnection();
    @Override
    public void insert(Avocat avocat) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("INSERT INTO avocat (Nom, prenom,adress,description,rendez_vous,specialite,telephone) VALUES (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, avocat.getNom());
            ps.setString(2, avocat.getPrenom());
            ps.setString(3, avocat.getAdress());
            ps.setString(4, avocat.getDescription());
            ps.setString(5, avocat.getRendez_vous());
            ps.setString(6, avocat.getSpecialite());
            ps.setString(7, avocat.getTelephone());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    int id = rs.getInt(1);

                    avocat.setId(id);
                }

                DB.closeResultSet(rs);
            } else {
                System.out.println("Aucune ligne renvoyée");
            }
        } catch (SQLException e) {
            System.err.println("problème d'insertion d'un avocat");;
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void update(Avocat avocat) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("UPDATE avocat SET Nom = ?,prenom = ?,adress = ?,description = ?,rendez_vous = ?,specialite = ?,telephone = ? WHERE Id = ?");

            ps.setString(1, avocat.getNom());
            ps.setString(2, avocat.getPrenom());
            ps.setString(3, avocat.getAdress());
            ps.setString(4, avocat.getDescription());
            ps.setString(5, avocat.getRendez_vous());
            ps.setString(6, avocat.getSpecialite());
            ps.setString(7, avocat.getTelephone());
            ps.setInt(8, avocat.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e);;
        } finally {
            DB.closeStatement(ps);
        }

    }




















    @Override
    public void deleteById(Integer id) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("DELETE FROM avocat WHERE id = ?");

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("problème de suppression d'un avocat");;
        } finally {
            DB.closeStatement(ps);
        }

    }

    @Override
    public Avocat findById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM avocat WHERE id = ?");

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                Avocat avocat = new Avocat();

                avocat.setId(rs.getInt("Id"));
                avocat.setNom(rs.getString("Nom"));
                avocat.setPrenom(rs.getString("prenom"));
                avocat.setAdress(rs.getString("adress"));
                avocat.setDescription(rs.getString("description"));
                avocat.setRendez_vous(rs.getString("rendez_vous"));
                avocat.setSpecialite(rs.getString("specialite"));
                avocat.setTelephone(rs.getString("telephone"));

                return avocat;
            }

            return null;
        } catch (SQLException e) {
            System.err.println("problème de requête pour trouver un avocat");;
            return null;
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }

    }

    @Override
    public List<Avocat> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM avocat");
            rs = ps.executeQuery();

            List<Avocat> listAvocat = new ArrayList<>();

            while (rs.next()) {
                Avocat avocat = new Avocat();

                avocat.setId(rs.getInt("Id"));
                avocat.setNom(rs.getString("nom"));
                avocat.setPrenom(rs.getString("prenom"));
                avocat.setAdress(rs.getString("adress"));
                avocat.setDescription(rs.getString("description"));
                avocat.setRendez_vous(rs.getString("rendez_vous"));
                avocat.setSpecialite(rs.getString("specialite"));
                avocat.setTelephone(rs.getString("telephone"));

                listAvocat.add(avocat);
            }

            return listAvocat;
        } catch (SQLException e) {
            System.err.println("problème de requête pour sélectionner un avocat");;
            return null;
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }

    }
}

