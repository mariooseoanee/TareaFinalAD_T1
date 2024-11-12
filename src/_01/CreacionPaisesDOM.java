package _01;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class CreacionPaisesDOM {

    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        DOMImplementation imp = db.getDOMImplementation();
        Document doc = imp.createDocument(null, "Paises", null);
        doc.setXmlVersion("1.0");

        String[] paises = { "Belice", "El Salvador", "Guatemala", "Honduras", "Nicaragua", "México", "Panamá", "Costa Rica" };
        String[] presidentes = { "Froyla Tzalam", "Nayib Bukele", "Alejandro Giammattei", "Xiomara Castro",
                                 "Daniel Ortega", "Andrés Manuel López Obrador", "Laurentino Cortizo", "Rodrigo Chaves" };
        Long[] pib = { 1987000000L, 74818000000L, 185474000000L, 85625000000L, 47770000000L,
                       2890685000000L, 128500000000L, 129950000000L };
        Double[] coGini = { 53.3, 38.8, 48.3, 48.2, 46.2, 45.4, 50.9, 47.2 };

        for (int i = 0; i < paises.length; i++) {
            Element elemPais = doc.createElement("Pais");
            doc.getDocumentElement().appendChild(elemPais);

            Element elemNombrePais = doc.createElement("nombre");
            elemPais.appendChild(elemNombrePais);
            Text textoNombre = doc.createTextNode(paises[i]);
            elemNombrePais.appendChild(textoNombre);

            Element elemPresidente = doc.createElement("presidente");
            elemPais.appendChild(elemPresidente);
            Text textoPresidente = doc.createTextNode(presidentes[i]);
            elemPresidente.appendChild(textoPresidente);

            Element elemPib = doc.createElement("pib");
            elemPais.appendChild(elemPib);
            Text textoPib = doc.createTextNode(pib[i].toString());
            elemPib.appendChild(textoPib);

            Element elemGini = doc.createElement("coeficienteDeGini");
            elemPais.appendChild(elemGini);
            Text textoGini = doc.createTextNode(coGini[i].toString());
            elemGini.appendChild(textoGini);
        }

        DOMSource fuente = new DOMSource(doc);
        StreamResult resultado = new StreamResult(new File("paises.xml"));
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.transform(fuente, resultado);

        StreamResult consola = new StreamResult(System.out);
        transformer.transform(fuente, consola);
    }
}
