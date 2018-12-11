<%@page import="java.sql.Date"%>
<%@page import="utiles.Utiles"%>
<%@page import="utiles.Conexion"%>
<%@page import="net.sf.jasperreports.engine.JasperExportManager"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page contentType="application/pdf" %>

<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.naming.InitialContext"%>
<%
    String dirPath = "/rpt";
    String realPath = this.getServletContext().getRealPath(dirPath);
    String listado = request.getParameter("l");
    int id = Integer.parseInt(request.getParameter("id"));
    /*int desde = Integer.parseInt(request.getParameter("d"));
    int hasta = Integer.parseInt(request.getParameter("h"));*/
    String jasperReport = listado + ".jasper";
    JasperPrint print = null;
    Connection conn = null;

    Date desde = Date.valueOf(request.getParameter("d").toString());
    Date hasta = Date.valueOf(request.getParameter("h").toString());

    try {
        Conexion.conectar();
        conn = Conexion.getConn();
        Map parameters = new HashMap();
        parameters.put("DESDE", desde);
        parameters.put("HASTA", hasta);
        parameters.put("CLIENTE", id);
        System.out.println("DESDE: " + desde + " HASTA: " + hasta);
        print = JasperFillManager.fillReport(realPath + "//" + jasperReport, parameters, conn);
        response.setContentType("application/pdf");
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        response.getOutputStream().flush();
        response.getOutputStream().close();
    } catch (Exception ex) {
        ex.printStackTrace();
        System.out.println(ex.getMessage());
    } finally {
        if (conn != null) {
            conn.close();
        }
    }
%>

