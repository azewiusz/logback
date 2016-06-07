/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testngapppender;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.joran.util.ConfigurationWatchListUtil;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author tomasz.kosinski
 */
public class XPathService {
    
    /**
     * Extract single xpath element from logback configuration file
     * @param xpathp
     * @return 
     */
    public static String extractXPathValue(String xpathp)
    {
        LoggerContext loggerContext = ((ch.qos.logback.classic.Logger)LoggerFactory.getLogger(XPathService.class)).getLoggerContext();
        URL mainURL = ConfigurationWatchListUtil.getMainWatchURL(loggerContext);
        
       // System.out.println(""+mainURL.toString());
        
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();        
            dbf.setNamespaceAware(false);
            
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(mainURL.openStream());
            
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = xpath.compile(xpathp);
            
            
            Node nl = (Node) expr.evaluate(doc, XPathConstants.NODE);
            
            if (nl == null) return null;
            
            return nl.getTextContent();
            
            
            
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException ex) {
            Logger.getLogger(XPathService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
