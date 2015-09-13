/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Connections.RuthDBConnection;
import java.sql.Connection;

/**
 *
 * @author Glenn Benscoter
 */
public class reservationsFactory
{
    public static String getReservationWithinDays(String Days )
    { 
        Connection con = RuthDBConnection.getConnection();
        return Days;
    }
}
