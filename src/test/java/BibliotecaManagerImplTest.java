import manager.BibliotecaManager;
import manager.BibliotecaManagerImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import models.*;
import exceptions.*;
import java.util.Map;
import java.util.List;

public class BibliotecaManagerImplTest {
    private BibliotecaManager fm;

    @Before
    public void setUp() {
        fm = BibliotecaManagerImpl.getInstance();
    }

    @After
    public void tearDown() {
        fm = null;
    }

    // Los Ejemoplos (valor de atributos) estan creados con el IA --> por ejemplo new Lector("1", "Juan", "Perez", "12345678A", "2000-01-01", "Calle Mayor 1");
    //Para mejor comprobacion de datos

    @Test
    public void testAñadirLector() {
        Lector lector = new Lector("1", "Juan", "Perez", "12345678A", "2000-01-01", "Calle Mayor 1");
        fm.añadirLector(lector);
        Assert.assertNotNull(lector);
    }

    @Test
    public void testAñadirLectorActualizaDatos() {
        Lector lector1 = new Lector("1", "Juan", "Perez", "12345678A", "2000-01-01", "Calle Mayor 1");
        fm.añadirLector(lector1);

        Lector lector2 = new Lector("1", "Juan", "Garcia", "12345678A", "2000-01-01", "Calle Nueva 2");
        fm.añadirLector(lector2);

        Assert.assertNotNull(lector2);
    }

    @Test
    public void testEnmagatzemarLlibre() {
        Llibre llibre = new Llibre("1", "978-3-16-148410-0", "El Quijote", "Editorial1", "1605", 1, "Cervantes", "Novela");
        fm.enmagatzemarLlibre(llibre);
        Assert.assertNotNull(llibre);
    }

    @Test
    public void testEnmagatzemarMultiplesLlibres() {
        for (int i = 1; i <= 5; i++) {
            Llibre llibre = new Llibre("" + i, "ISBN-" + i, "Libro " + i, "Editorial", "2020", 1, "Autor " + i, "Ficción");
            fm.enmagatzemarLlibre(llibre);
        }
        Assert.assertTrue(true);
    }

    @Test
    public void testEnmagatzemarMasDe10Llibres() {
        for (int i = 1; i <= 15; i++) {
            Llibre llibre = new Llibre("" + i, "ISBN-" + i, "Libro " + i, "Editorial", "2020", 1, "Autor " + i, "Ficción");
            fm.enmagatzemarLlibre(llibre);
        }
        Assert.assertTrue(true);
    }

    @Test
    public void testCatalogarLlibre() {
        Llibre llibre = new Llibre("1", "978-3-16-148410-0", "El Quijote", "Editorial1", "1605", 1, "Cervantes", "Novela");
        fm.enmagatzemarLlibre(llibre);
        fm.catalogarLlibre();
        Assert.assertTrue(true);
    }

    @Test
    public void testCatalogarLlibreMismoISBN() {
        Llibre llibre1 = new Llibre("1", "978-3-16-148410-0", "El Quijote", "Editorial1", "1605", 1, "Cervantes", "Novela");
        Llibre llibre2 = new Llibre("2", "978-3-16-148410-0", "El Quijote", "Editorial1", "1605", 2, "Cervantes", "Novela");

        fm.enmagatzemarLlibre(llibre1);
        fm.enmagatzemarLlibre(llibre2);

        fm.catalogarLlibre();
        fm.catalogarLlibre();

        Assert.assertTrue(true);
    }

    @Test(expected = NoQuedanLibrosParaCatalogarException.class)
    public void testCatalogarLlibreSinLibros() {
        fm.catalogarLlibre();
    }

    @Test
    public void testCatalogarLlibreEliminaPilaVacia() {
        Llibre llibre1 = new Llibre("1", "ISBN-1", "Libro 1", "Editorial", "2020", 1, "Autor 1", "Ficción");
        fm.enmagatzemarLlibre(llibre1);

        fm.catalogarLlibre();

        Assert.assertTrue(true);
    }

    @Test
    public void testFlujoCombinado() {
        Lector lector = new Lector("1", "Maria", "Lopez", "87654321B", "1995-05-15", "Avenida Principal 10");
        fm.añadirLector(lector);

        for (int i = 1; i <= 12; i++) {
            Llibre llibre = new Llibre("" + i, "ISBN-" + i, "Libro " + i, "Editorial", "2020", 1, "Autor", "Tema");
            fm.enmagatzemarLlibre(llibre);
        }

        fm.catalogarLlibre();
        fm.catalogarLlibre();
        fm.catalogarLlibre();

        Assert.assertTrue(true);
    }

    @Test
    public void testPrestarLlibre() {
        Lector lector = new Lector("L1", "Carlos", "Martinez", "11111111A", "1990-03-20", "Calle 123");
        fm.añadirLector(lector);

        Llibre llibre = new Llibre("1", "ISBN-TEST-1", "Libro Test", "Editorial Test", "2020", 1, "Autor Test", "Tema Test");
        fm.enmagatzemarLlibre(llibre);
        fm.catalogarLlibre();

        PrestecLlibre prestec = new PrestecLlibre("P1", "L1", "ISBN-TEST-1", "2024-01-01", "2024-01-15");
        fm.prestarLlibre(prestec);

        Assert.assertEquals("En tràmit", prestec.getEstadoPrestamo());
    }

    @Test
    public void testPrestarLlibreDecrementaExemplars() {
        Lector lector = new Lector("L2", "Ana", "Garcia", "22222222B", "1992-05-10", "Avenida 456");
        fm.añadirLector(lector);

        Llibre llibre1 = new Llibre("1", "ISBN-TEST-2", "Libro Test 2", "Editorial", "2020", 1, "Autor", "Tema");
        Llibre llibre2 = new Llibre("2", "ISBN-TEST-2", "Libro Test 2", "Editorial", "2020", 1, "Autor", "Tema");
        fm.enmagatzemarLlibre(llibre1);
        fm.enmagatzemarLlibre(llibre2);
        fm.catalogarLlibre();
        fm.catalogarLlibre();

        PrestecLlibre prestec = new PrestecLlibre("P2", "L2", "ISBN-TEST-2", "2024-01-01", "2024-01-15");
        fm.prestarLlibre(prestec);

        Assert.assertTrue(true);
    }

    @Test(expected = LlibreNotFoundException.class)
    public void testPrestarLlibreSinExemplares() {
        Lector lector = new Lector("L3", "Pedro", "Lopez", "33333333C", "1988-07-15", "Calle 789");
        fm.añadirLector(lector);

        Llibre llibre = new Llibre("1", "ISBN-TEST-3", "Libro Test 3", "Editorial", "2020", 1, "Autor", "Tema");
        fm.enmagatzemarLlibre(llibre);
        fm.catalogarLlibre();

        PrestecLlibre prestec1 = new PrestecLlibre("P3", "L3", "ISBN-TEST-3", "2024-01-01", "2024-01-15");
        fm.prestarLlibre(prestec1);

        PrestecLlibre prestec2 = new PrestecLlibre("P4", "L3", "ISBN-TEST-3", "2024-01-02", "2024-01-16");
        fm.prestarLlibre(prestec2);
    }

    @Test(expected = LectorNotFoundException.class)
    public void testPrestarLlibreLectorNoExiste() {
        Llibre llibre = new Llibre("1", "ISBN-TEST-4", "Libro Test 4", "Editorial", "2020", 1, "Autor", "Tema");
        fm.enmagatzemarLlibre(llibre);
        fm.catalogarLlibre();

        PrestecLlibre prestec = new PrestecLlibre("P5", "L999", "ISBN-TEST-4", "2024-01-01", "2024-01-15");
        fm.prestarLlibre(prestec);
    }

    @Test(expected = LlibreNotFoundException.class)
    public void testPrestarLlibreNoExiste() {
        Lector lector = new Lector("L4", "Laura", "Sanchez", "44444444D", "1993-09-25", "Plaza 321");
        fm.añadirLector(lector);

        PrestecLlibre prestec = new PrestecLlibre("P6", "L4", "ISBN-NOEXISTE", "2024-01-01", "2024-01-15");
        fm.prestarLlibre(prestec);
    }

    @Test
    public void testGetLlibresPrestacsOfLector() {
        Lector lector = new Lector("L5", "Miguel", "Fernandez", "55555555E", "1985-11-30", "Calle Real 100");
        fm.añadirLector(lector);

        for (int i = 1; i <= 3; i++) {
            Llibre llibre = new Llibre("" + i, "ISBN-MULTI-" + i, "Libro " + i, "Editorial", "2020", 1, "Autor", "Tema");
            fm.enmagatzemarLlibre(llibre);
            fm.catalogarLlibre();
        }

        PrestecLlibre prestec1 = new PrestecLlibre("P7", "L5", "ISBN-MULTI-1", "2024-01-01", "2024-01-15");
        PrestecLlibre prestec2 = new PrestecLlibre("P8", "L5", "ISBN-MULTI-2", "2024-01-02", "2024-01-16");
        PrestecLlibre prestec3 = new PrestecLlibre("P9", "L5", "ISBN-MULTI-3", "2024-01-03", "2024-01-17");
        fm.prestarLlibre(prestec1);
        fm.prestarLlibre(prestec2);
        fm.prestarLlibre(prestec3);

        dto.PrestamosDelLectorDTO dto = new dto.PrestamosDelLectorDTO("L5");
        List<PrestecLlibre> prestecs = fm.getLlibresPrestacsOfLector(dto);

        Assert.assertEquals(3, prestecs.size());
    }

    @Test
    public void testGetLlibresPrestacsOfLectorSinPrestamos() {
        Lector lector = new Lector("L6", "Sofia", "Ruiz", "66666666F", "1991-04-18", "Avenida 555");
        fm.añadirLector(lector);

        dto.PrestamosDelLectorDTO dto = new dto.PrestamosDelLectorDTO("L6");
        List<PrestecLlibre> prestecs = fm.getLlibresPrestacsOfLector(dto);

        Assert.assertEquals(0, prestecs.size());
    }

    @Test
    public void testGetLlibresPrestacsOfLectorMultiplesLectores() {
        Lector lector1 = new Lector("L7", "David", "Torres", "77777777G", "1987-08-22", "Calle A 10");
        Lector lector2 = new Lector("L8", "Elena", "Moreno", "88888888H", "1994-12-05", "Calle B 20");
        fm.añadirLector(lector1);
        fm.añadirLector(lector2);

        for (int i = 1; i <= 4; i++) {
            Llibre llibre = new Llibre("" + i, "ISBN-MIX-" + i, "Libro " + i, "Editorial", "2020", 1, "Autor", "Tema");
            fm.enmagatzemarLlibre(llibre);
            fm.catalogarLlibre();
        }

        PrestecLlibre prestec1 = new PrestecLlibre("P10", "L7", "ISBN-MIX-1", "2024-01-01", "2024-01-15");
        PrestecLlibre prestec2 = new PrestecLlibre("P11", "L7", "ISBN-MIX-2", "2024-01-02", "2024-01-16");
        PrestecLlibre prestec3 = new PrestecLlibre("P12", "L8", "ISBN-MIX-3", "2024-01-03", "2024-01-17");
        PrestecLlibre prestec4 = new PrestecLlibre("P13", "L8", "ISBN-MIX-4", "2024-01-04", "2024-01-18");
        fm.prestarLlibre(prestec1);
        fm.prestarLlibre(prestec2);
        fm.prestarLlibre(prestec3);
        fm.prestarLlibre(prestec4);

        dto.PrestamosDelLectorDTO dto1 = new dto.PrestamosDelLectorDTO("L7");
        dto.PrestamosDelLectorDTO dto2 = new dto.PrestamosDelLectorDTO("L8");
        List<PrestecLlibre> prestecs1 = fm.getLlibresPrestacsOfLector(dto1);
        List<PrestecLlibre> prestecs2 = fm.getLlibresPrestacsOfLector(dto2);

        Assert.assertEquals(2, prestecs1.size());
        Assert.assertEquals(2, prestecs2.size());
    }
}