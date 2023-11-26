/*
 *  Eleciones IT5N por Hector Armando Herrera
 *
 * Este programa es software libre: usted puede distribuir y/o modificarlo
 * bajo los términos de la GNU General Public License publicada por
 * la Free Software Foundation, ya sea la versión 3 de la Licencia, o
 * versiones posteriores.
 *
 * Este programa se distribuye con la esperanza de que sea útil.,
 * pero SIN NINGUNA GARANTÍA; sin ni siquiera la garantía implícita de
 * COMERCIABILIDAD o APTITUD PARA UN PROPÓSITO PARTICULAR. Ver la
 * GNU General Public License para mas detalles.
 *
 * Debería haber recibido una copia de la GNU General Public License
 * junto con este programa. Si no, visite <http://www.gnu.org/licenses/>.
 */

package com.teupa.basedatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Héctor Armando Herrera (profeharmando@gmail.com)
 */
public class Crear {    
    
    //Crear la tabla tbl_resultado_final
    public static boolean tbl_resultado_final(Connection conn){
        String sentenciaSQL = "CREATE TABLE IF NOT EXISTS tbl_resultado_final (\n" +
        "  codigo INT(11) NOT NULL AUTO_INCREMENT,\n" +
        "  grado VARCHAR(15) NOT NULL,\n" +
        "  inicial_n SMALLINT(5) NOT NULL,\n" +
        "  inicial_v SMALLINT(5) NOT NULL,\n" +
        "  inicial_t SMALLINT(5) NOT NULL,\n" +
        "  final_n SMALLINT(5) NOT NULL,\n" +
        "  final_v SMALLINT(5) NOT NULL,\n" +
        "  final_t SMALLINT(5) NOT NULL,\n" +
        "  ingre_n SMALLINT(5) NOT NULL,\n" +
        "  ingre_v SMALLINT(5) NOT NULL,\n" +
        "  ingre_t SMALLINT(5) NOT NULL,\n" +
        "  trasla_n SMALLINT(5) NOT NULL,\n" +
        "  trasla_v SMALLINT(5) NOT NULL,\n" +
        "  trasla_t SMALLINT(5) NOT NULL,\n" +
        "  deser_n SMALLINT(5) NOT NULL,\n" +
        "  deser_v SMALLINT(5) NOT NULL,\n" +
        "  deser_t SMALLINT(5) NOT NULL,\n" +
        "  evalu_n SMALLINT(5) NOT NULL,\n" +
        "  evalu_v SMALLINT(5) NOT NULL,\n" +
        "  evalu_t SMALLINT(5) NOT NULL,\n" +
        "  inic_n SMALLINT(5) NOT NULL,\n" +
        "  inic_v SMALLINT(5) NOT NULL,\n" +
        "  inic_t SMALLINT(5) NOT NULL,\n" +
        "  basi_n SMALLINT(5) NOT NULL,\n" +
        "  basi_v SMALLINT(5) NOT NULL,\n" +
        "  basi_t SMALLINT(5) NOT NULL,\n" +
        "  avan_n SMALLINT(5) NOT NULL,\n" +
        "  avan_v SMALLINT(5) NOT NULL,\n" +
        "  avan_t SMALLINT(5) NOT NULL,\n" +
        "  exce_n SMALLINT(5) NOT NULL,\n" +
        "  exce_v SMALLINT(5) NOT NULL,\n" +
        "  exce_t SMALLINT(5) NOT NULL,\n" +
        "  codigo_centro INT(11) NOT NULL,\n" +
        "  PRIMARY KEY (codigo),\n" +
        "  CONSTRAINT fk_tbl_resultado_final_1\n" +
        "    FOREIGN KEY (codigo_centro)\n" +
        "    REFERENCES tbl_centro(codigo)\n" +
        "    ON DELETE NO ACTION\n" +
        "    ON UPDATE NO ACTION)\n" +
        "ENGINE = InnoDB\n" +
        "DEFAULT CHARACTER SET = utf8\n" +
        "COLLATE = utf8_spanish_ci";
        try {
            Statement instruccion = conn.createStatement();
            instruccion.execute(sentenciaSQL);
            System.out.println("\n\tLa tabla tbl_resultado_final ha sido creada");
            return true;
        } catch (SQLException ex) {
            System.err.println(
                        "Error de Base de Datos tratando de crear la tabla tbl_resultado_final.\n"+
                        "Sentencia SQL: \""+sentenciaSQL+"\"\n"+
                        "Mensaje de error(SQL): " + ex.getMessage()+"\n"
            );
            return false;
        }
    }
    
    //Crear la tabla tbl_resultado_final
    public static boolean tbl_reprobados(Connection conn){
        String sentenciaSQL = "CREATE TABLE IF NOT EXISTS tbl_reprobados (\n" +
        "  codigo INT(11) NOT NULL AUTO_INCREMENT,\n" +
        "  grado VARCHAR(15) NOT NULL,\n" +
        "  inicial_n SMALLINT(5) NOT NULL,\n" +
        "  inicial_v SMALLINT(5) NOT NULL,\n" +
        "  inicial_t SMALLINT(5) NOT NULL,\n" +
        "  final_n SMALLINT(5) NOT NULL,\n" +
        "  final_v SMALLINT(5) NOT NULL,\n" +
        "  final_t SMALLINT(5) NOT NULL,\n" +
        "  eval_esp_n SMALLINT(5) NOT NULL,\n" +
        "  eval_esp_v SMALLINT(5) NOT NULL,\n" +
        "  eval_esp_t SMALLINT(5) NOT NULL,\n" +
        "  rep_esp_n SMALLINT(5) NOT NULL,\n" +
        "  rep_esp_v SMALLINT(5) NOT NULL,\n" +
        "  rep_esp_t SMALLINT(5) NOT NULL,\n" +
        "  tasa_esp_n DOUBLE NOT NULL,\n" +
        "  tasa_esp_v DOUBLE NOT NULL,\n" +
        "  tasa_esp_t DOUBLE NOT NULL,\n" +
        "  eval_mat_n SMALLINT(5) NOT NULL,\n" +
        "  eval_mat_v SMALLINT(5) NOT NULL,\n" +
        "  eval_mat_t SMALLINT(5) NOT NULL,\n" +
        "  rep_mat_n SMALLINT(5) NOT NULL,\n" +
        "  rep_mat_v SMALLINT(5) NOT NULL,\n" +
        "  rep_mat_t SMALLINT(5) NOT NULL,\n" +
        "  tasa_mat_n DOUBLE NOT NULL,\n" +
        "  tasa_mat_v DOUBLE NOT NULL,\n" +
        "  tasa_mat_t DOUBLE NOT NULL,\n" +
        "  codigo_centro INT(11) NOT NULL,\n" +
        "  PRIMARY KEY (codigo),\n" +
        "  CONSTRAINT fk_tbl_reprobados_1\n" +
        "    FOREIGN KEY (codigo_centro)\n" +
        "    REFERENCES tbl_centro(codigo)\n" +
        "    ON DELETE NO ACTION\n" +
        "    ON UPDATE NO ACTION)\n" +
        "ENGINE = InnoDB\n" +
        "DEFAULT CHARACTER SET = utf8\n" +
        "COLLATE = utf8_spanish_ci";
        try {
            Statement instruccion = conn.createStatement();
            instruccion.execute(sentenciaSQL);
            System.out.println("\n\tLa tabla tbl_reprobados ha sido creada");
            return true;
        } catch (SQLException ex) {
            System.err.println(
                        "Error de Base de Datos tratando de crear la tabla tbl_reprobados.\n"+
                        "Sentencia SQL: \""+sentenciaSQL+"\"\n"+
                        "Mensaje de error(SQL): " + ex.getMessage()+"\n"
            );
            return false;
        }
    }
    
    
    //Crear la tabla para los datos del Centro
    public static boolean tbl_centro(Connection conn){
        String sentenciaSQL = "CREATE TABLE IF NOT EXISTS tbl_centro (\n" +
            " codigo INT(11) NOT NULL AUTO_INCREMENT,\n" +
            " centro VARCHAR(60) NOT NULL,\n" +
            " codigo_sace VARCHAR(12) NOT NULL,\n" +
            " administracion VARCHAR(15) NOT NULL,\n" +
            " zona VARCHAR(10) NOT NULL,\n" +
            " nivel VARCHAR(15) NOT NULL,\n" +
            " ciclo VARCHAR(20) NOT NULL,\n" +
            " jornada VARCHAR(25) NOT NULL,\n" +
            " cobertura VARCHAR(10) NOT NULL,\n" +
            " matricula INT(11) NOT NULL,\n" +
            " PRIMARY KEY (codigo))\n" +
            " ENGINE = InnoDB\n" +
            " DEFAULT CHARACTER SET = utf8\n" +
            " COLLATE = utf8_spanish_ci";
        try {
            Statement instruccion = conn.createStatement();
            instruccion.execute(sentenciaSQL);
            System.out.println("\n\tLa tabla tbl_centro ha sido creada.");
            return true;
        } catch (SQLException ex) {
            System.err.println(
                        "Error de Base de Datos tratando de crear la tabla tbl_centro.\n"+
                        "Sentencia SQL: \""+sentenciaSQL+"\"\n"+
                        "Mensaje de error(SQL): " + ex.getMessage()+"\n"
            );
            return false;
        }    
    }
    
}
