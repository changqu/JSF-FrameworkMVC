package com.sdi.tests.Tests;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.sdi.tests.pageobjects.PO_CancelViaje;
import com.sdi.tests.pageobjects.PO_EditViaje;
import com.sdi.tests.pageobjects.PO_Identificacion;
import com.sdi.tests.pageobjects.PO_RegViaje;
import com.sdi.tests.pageobjects.PO_Registro;
import com.sdi.tests.pageobjects.PO_ViajeAcept;
import com.sdi.tests.utils.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class SDI2_Tests {

	WebDriver driver; 
	List<WebElement> elementos = null;
	
	public SDI2_Tests()
	{
	}

	@Before
	public void run()//de principio hacemos lo siguiente
	{
		//Creamos el driver para Firefox. Recomendable usar Firefox.
		driver = new FirefoxDriver(); 
		//Vamos a la página principal de mi aplicación
		driver.get("http://localhost:8280/sdi2-180/");			
	}
	@After
	public void end()//al final de las pruebas hacemos estos
	{
		//Cerramos el navegador
		driver.quit();
	}

	//PRUEBAS, las pruebas son independientes, dado que si termina un @test se apaga el navegador y 
	//luego que si empieza el nuevo @test se reinicia el navegador
	
	public void testRegistrarseParametros(String nombreform, String identificador, String nombre, String apellidos, 
			String correo, String password, String repetirPassword) {
		//Pinchamos la opcion de registrarse
		SeleniumUtils.ClickOpcion(driver, "form-cabecera:linkregistrarse");

		//Esperamos a que se cargue la pagina de registrarse
		SeleniumUtils.EsperaCargaPagina(driver, "id", nombreform, 10); 

		//Vamos a rellenar el formulario
		new PO_Registro().rellenaFormulario(driver, identificador, nombre, apellidos, correo, password, repetirPassword);
	}
	
	public void testIdentificacionParametros(String nombreform, String identificador, String password) {
		//Esperamos a que se cargue la pagina de registrarse
		SeleniumUtils.EsperaCargaPagina(driver, "id", nombreform, 10); 

		//Vamos a rellenar el formulario
		new PO_Identificacion().rellenaFormulario(driver, identificador, password);
	}
	
	public void testRegistrarViaje(){
		testIdentificacionParametros("btLogin", "user1", "user1");
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "linkregistrarviaje", 10);
    	SeleniumUtils.ClickOpcion(driver, "form-cabecera:linkregistrarviaje");
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "btRegistrarViaje", 10);
	}
	
	public void testModificarViaje(){
		testIdentificacionParametros("btLogin", "user3", "user3");
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "linkmisviajes", 10);
    	SeleniumUtils.ClickOpcion(driver, "form-cabecera:linkmisviajes");
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "modificar", 10);
    	//obtener lista de elementos del boton modificar
    	elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "modificar", 10);
    	elementos.get(0).click();
	}
	
	public void testCancelarViaje(){
		testIdentificacionParametros("btLogin", "user4", "user4");
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "linkmisviajes", 10);
    	SeleniumUtils.ClickOpcion(driver, "form-cabecera:linkmisviajes");
    	//elementos checkBox
    	elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "selectViaje", 10);
    	//elemento boton cancelarViaje
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "btCancelarViaje", 10);
	}
	
	public void solicitarPlaza(int index) throws InterruptedException{
		SeleniumUtils.EsperaCargaPagina(driver, "id", "linkmisviajes", 10);
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "linklistaviajes", 10);
    	SeleniumUtils.ClickOpcion(driver, "form-cabecera:linklistaviajes");
    	elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "verDetalle", 10);
    	elementos.get(index).click();
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "btSolicitarPlaza", 10);
    	new PO_ViajeAcept().solicitarPlaza(driver);
    	Thread.sleep(2000);
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "linkindex", 10);
    	SeleniumUtils.ClickOpcion(driver, "form-cabecera:linkindex");
    	Thread.sleep(2000);
	}
	public void confirmarPasajero(int index) throws InterruptedException{
		SeleniumUtils.EsperaCargaPagina(driver, "id", "linkmisviajes", 10);
    	SeleniumUtils.ClickOpcion(driver, "form-cabecera:linkmisviajes");
    	elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "ver", 10);
    	elementos.get(index).click();
    	Thread.sleep(1000);
    	elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "btConfirmarPasajero", 10);
	}
	
	public void i18N() throws InterruptedException{
		Thread.sleep(3000);
		SeleniumUtils.ClickSubopcionMenuHover(driver, "form-cabecera:submenuidioma", "form-cabecera:linkingles");
    	Thread.sleep(1000);
		SeleniumUtils.EsperaCargaPagina(driver, "id", "linkregistrarse", 10);
    	SeleniumUtils.ClickOpcion(driver, "form-cabecera:linkregistrarse");
    	Thread.sleep(1000);
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "linklistaviajes", 10);
    	SeleniumUtils.ClickOpcion(driver, "form-cabecera:linklistaviajes");
    	Thread.sleep(1000);
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "linklogin", 10);
    	SeleniumUtils.ClickOpcion(driver, "form-cabecera:linklogin");
    	Thread.sleep(1000);
    	testIdentificacionParametros("btLogin", "user6", "user6");
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "misviajes", 10);
    	Thread.sleep(1000);
    	SeleniumUtils.ClickSubopcionMenuHover(driver, "form-cabecera:submenuidioma", "form-cabecera:linkchinese");
    	Thread.sleep(1000);
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "misviajes", 10);
    	SeleniumUtils.ClickOpcion(driver, "form-cabecera:linkmisviajes");
    	Thread.sleep(2000);
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "linkregistrarviaje", 10);
    	SeleniumUtils.ClickOpcion(driver, "form-cabecera:linkregistrarviaje");
    	Thread.sleep(1000);
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "linklistaviajes", 10);
    	SeleniumUtils.ClickOpcion(driver, "form-cabecera:linklistaviajes");
    	Thread.sleep(1000);
	}
	
	//	1.	[RegVal] Registro de Usuario con datos válidos.
	@Test	
    public void t01_RegVal() throws InterruptedException {
		testRegistrarseParametros("btRegistrarse", "user18", "Juan", "Manuel", "juan@uniovi.es", "user18", "user18");
		SeleniumUtils.EsperaCargaPagina(driver, "id", "msgRegistrarse", 10);
		Thread.sleep(1000);
		SeleniumUtils.EsperaCargaPagina(driver, "id", "linklogin", 10);
		SeleniumUtils.ClickOpcion(driver, "form-cabecera:linklogin");
		testIdentificacionParametros("btLogin", "user18", "user18");
		Thread.sleep(1000);
    }
	//	2.	[RegInval] Registro de Usuario con datos inválidos (contraseñas diferentes).
    @Test
    public void t02_RegInval() throws InterruptedException {
    	testRegistrarseParametros("btRegistrarse", "user666", "Juan", "Manuel", "juan@uniovi.es", "user666", "user777");
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "msgRegistrarse", 10);
    	Thread.sleep(1000);
    }
    //	2. [RegInval2] Registro de Usuario con datos inválidos (identificador ya existe).
	@Test
	public void t02_RegInval2() throws InterruptedException {
		testRegistrarseParametros("btRegistrarse", "user6", "Juan", "Manuel", "juan@uniovi.es", "user6", "user6");
		SeleniumUtils.EsperaCargaPagina(driver, "id", "msgRegistrarse", 10);
		Thread.sleep(1000);
	}
	//	2. [RegInval3] Registro de Usuario con datos inválidos (falta de datos).
	@Test
	public void t02_RegInval3() throws InterruptedException {
		testRegistrarseParametros("btRegistrarse", "user6", "Juan", "", "", "", "");
		SeleniumUtils.EsperaCargaPagina(driver, "id", "btRegistrarse", 10);
		Thread.sleep(1000);
	}
	//	3.	[IdVal] Identificación de Usuario registrado con datos válidos.
    @Test
    public void t03_IdVal() throws InterruptedException {
    	testIdentificacionParametros("btLogin", "user1", "user1");
    	//Esperamos a que se cargue la pagina principal
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "linkprincipal", 10); 
    	Thread.sleep(1000);
    }
	//	4.	[IdInval] Identificación de usuario registrado con datos inválido(Password invalido).
    @Test
    public void t04_IdInval() throws InterruptedException {
    	testIdentificacionParametros("btLogin", "user1", "user2");
    	//Esperamos a que se cargue mensaje de login
    	SeleniumUtils.EsperaCargaPagina(driver, "text", "Usuario o password erróneos", 10);//hay que poner eso ya que salta la pagina
    	Thread.sleep(1000);
    }
    // 4.	[IdInval2] Identificación de usuario registrado con datos inválidos(falta datos).
    @Test
    public void t04_IdInval2() throws InterruptedException {
    	testIdentificacionParametros("btLogin", "user1", "");
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "btLogin", 10);
    	Thread.sleep(1000);
    }
	//	5.	[AccInval] Intento de acceso con URL desde un usuario no público (no identificado). Intento de acceso a vistas de acceso privado. 
    @Test
    public void t05_AccInval() throws InterruptedException {
    	//vistas publicos
    	Thread.sleep(1000);
    	driver.get("http://localhost:8280/sdi2-180/registrarse.xhtml");
    	Thread.sleep(1000);
    	//vista registrados
    	driver.get("http://localhost:8280/sdi2-180/registrado/principal.xhtml");
    	Thread.sleep(1000);
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "btLogin", 10);
    	driver.get("http://localhost:8280/sdi2-180/registrado/misViajes.xhtml");
    	Thread.sleep(1000);
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "btLogin", 10);
    }
	//	6.	[RegViajeVal] Registro de un viaje nuevo con datos válidos.
    @Test
    public void t06_RegViajeVal() throws InterruptedException {
    	testRegistrarViaje();
    	new PO_RegViaje().rellenaFormulario(driver);
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "msgRegistrarViaje", 10);
    	Thread.sleep(1000);
    }
	//	7.	[RegViajeInVal] Registro de un viaje nuevo con datos inválidos(Error relacion plaza).
    @Test
    public void t07_RegViajeInVal() throws InterruptedException {
    	testRegistrarViaje();
    	new PO_RegViaje().rellenaFormularioInvalido(driver, "6");
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "msgRegistrarViaje", 10);
    	Thread.sleep(1000);
    }
    //Opcional autocompletado del campo ciudad y provincia en Alta de viaje
	//	7.	[RegViajeInVal2Autocompletado] Registro de un viaje nuevo con datos inválidos(Falta de datos).
    @Test
    public void t07_RegViajeInVal2Autocompletado() throws InterruptedException {
    	testRegistrarViaje();  
    	new PO_RegViaje().rellenaCiudadProvincia(driver);
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "btRegistrarViaje", 10);
    	Thread.sleep(1000);
    }
	//	8.	[EditViajeVal] Edición de viaje existente con datos válidos.
    @Test
    public void t08_EditViajeVal() throws InterruptedException {
    	testModificarViaje();
    	new PO_EditViaje().rellenaFormulario(driver, "Me gusta viajar", "999999");
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "msgModificarViaje", 10);
    	Thread.sleep(1000);
    }
	//	9.	[EditViajeInVal] Edición de viaje existente con datos inválidos(no puede modificar plazas).
	@Test
    public void t09_EditViajeInVal() throws InterruptedException {
    	testModificarViaje();
    	new PO_EditViaje().rellenaFormularioInvalidoPlazaLibre(driver, "6");
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "msgModificarViaje", 10);
    	Thread.sleep(1000);
    }
	//	10.	[CancelViajeVal] Cancelación de un viaje existente por un promotor.
    @Test
    public void t10_CancelViajeVal() throws InterruptedException {
    	testCancelarViaje();
    	new PO_CancelViaje().cancelarUnViaje(driver, 
    			SeleniumUtils.EsperaCargaPagina(driver, "id", "btCancelarViaje", 10), elementos);
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "tablaMiViaje", 10);
    	//esperar mas tiempo para que pueda subir para arriba ver lo que sucedio
    	Thread.sleep(3000);
    }
	//	11.	[CancelMulViajeVal] Cancelación de múltiples viajes existentes por un promotor.
    @Test
    public void t11_CancelMulViajeVal() throws InterruptedException {
    	testCancelarViaje();
    	new PO_CancelViaje().cancelarMulViaje(driver, 
    			SeleniumUtils.EsperaCargaPagina(driver, "id", "btCancelarViaje", 10), elementos);
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "tablaMiViaje", 10);
    	//esperar mas tiempo para que pueda subir para arriba ver lo que sucedio
    	Thread.sleep(3000);
    }
	//	12.	[Ins1ViajeAceptVal] Inscribir en un viaje un solo usuario y ser admitido por el promotor.
    @Test
    public void t12_Ins1ViajeAceptVal() throws InterruptedException {
    	//user1 solicita plaza
    	testIdentificacionParametros("btLogin", "user1", "user1");
    	solicitarPlaza(1);
    	//user3 confirma pasajero
    	testIdentificacionParametros("btLogin", "user3", "user3");
    	confirmarPasajero(1);
    	new PO_ViajeAcept().confirmarPasajero(driver, elementos);
    	Thread.sleep(2000);
    }
	//	13.	[Ins2ViajeAceptVal] Inscribir en un viaje dos usuarios y ser admitidos los dos por el promotor.
    @Test
    public void t13_Ins2ViajeAceptVal() throws InterruptedException {
    	//user1 solicita plaza
    	testIdentificacionParametros("btLogin", "user1", "user1");
    	solicitarPlaza(3);
    	//user2 solicita plaza
    	testIdentificacionParametros("btLogin", "user2", "user2");
    	solicitarPlaza(3);
    	//user3 confirma plaza
    	testIdentificacionParametros("btLogin", "user3", "user3");
    	confirmarPasajero(3);
    	new PO_ViajeAcept().confirmarPasajeroDosPersona(driver, elementos);
    	Thread.sleep(2000);
    }
    //	14.	[Ins3ViajeAceptInval] Inscribir en un viaje (2 plazas máximo) dos usuarios y ser admitidos los dos y que un tercero intente inscribirse en ese mismo viaje pero ya no pueda por falta de plazas.
    @Test
    public void t14_Ins3ViajeAceptInval() throws InterruptedException {
    	//user4 solicita plaza
    	testIdentificacionParametros("btLogin", "user4", "user4");
    	solicitarPlaza(3);
    	//user5 solicita plaza
    	testIdentificacionParametros("btLogin", "user5", "user5");
    	solicitarPlaza(3);
    	//user6 solicita plaza
    	testIdentificacionParametros("btLogin", "user6", "user6");
    	solicitarPlaza(3);
    	//user3 confirma plaza
    	testIdentificacionParametros("btLogin", "user3", "user3");
    	confirmarPasajero(3);
    	new PO_ViajeAcept().confirmarPasajeroDosPersona(driver, elementos);
    	Thread.sleep(2000);
    }
    //	14.	[Ins3ViajeAceptInval] Inscribir en un viaje (2 plazas máximo) dos usuarios y ser admitidos los dos y que un tercero intente inscribirse en ese mismo viaje pero ya no pueda por falta de plazas.
    //el tercer usuario que ha solicitado plaza no le puede confirmar ya que no hay plaza
    @Test
    public void t14_Ins3ViajeAceptInvalContinuacion() throws InterruptedException {
    	testIdentificacionParametros("btLogin", "user3", "user3");
    	confirmarPasajero(3);
    	elementos.get(0).click();
    	SeleniumUtils.EsperaCargaPagina(driver, "text", "No se puede confirmar pasajero dado no hay plaza", 10);
    	Thread.sleep(3000);
    }
	//	15.	[CancelNoPromotorVal] Un usuario no promotor Cancela plaza.
    @Test
    public void t15_CancelNoPromotorVal() throws InterruptedException {
    	testIdentificacionParametros("btLogin", "user1", "user1");
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "linkmisviajes", 10);
    	SeleniumUtils.ClickOpcion(driver, "form-cabecera:linkmisviajes");
    	elementos=SeleniumUtils.EsperaCargaPagina(driver, "id", "verViajeRelacionado", 10);
    	elementos.get(0).click();
    	elementos=SeleniumUtils.EsperaCargaPagina(driver, "id", "btCancelarSolicitud", 10);
    	elementos.get(0).click();
    	Thread.sleep(3000);
    }
	//	16.	[Rech1ViajeVal] Inscribir en un viaje un usuario que será admitido y después rechazarlo por el promotor.
    @Test    //depende del prueba 13 y 14
    public void t16_Rech1ViajeVal() throws InterruptedException {
    	testIdentificacionParametros("btLogin", "user6", "user6");
    	solicitarPlaza(3);
    	testIdentificacionParametros("btLogin", "user3", "user3");
    	confirmarPasajero(4);
    	elementos.get(0).click();
    	elementos=SeleniumUtils.EsperaCargaPagina(driver, "id", "btExcluirPasajero", 10);
    	elementos.get(0).click();
    	SeleniumUtils.EsperaCargaPagina(driver, "text", "Pasajero excluido correctamente", 10);
    	Thread.sleep(2000);
    }
	//	17.	[i18N1] Cambio del idioma por defecto a un segundo idioma. (Probar algunas vistas)
    @Test
    public void t17_i18N1() throws InterruptedException {
    	i18N();
    }
	//	18.	[i18N2] Cambio del idioma por defecto a un segundo idioma y vuelta al idioma por defecto. (Probar algunas vistas)
    @Test
    public void t18_i18N2() throws InterruptedException {
    	i18N();
    	Thread.sleep(1000);
    	SeleniumUtils.ClickSubopcionMenuHover(driver, "form-cabecera:submenuidioma", "form-cabecera:linkespa");
    	Thread.sleep(1000);
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "misviajes", 10);
    	SeleniumUtils.ClickOpcion(driver, "form-cabecera:linkmisviajes");
    	Thread.sleep(2000);
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "linkregistrarviaje", 10);
    	SeleniumUtils.ClickOpcion(driver, "form-cabecera:linkregistrarviaje");
    	Thread.sleep(1000);
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "linklistaviajes", 10);
    	SeleniumUtils.ClickOpcion(driver, "form-cabecera:linklistaviajes");
    	Thread.sleep(1000);
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "linkindex", 10);
    	SeleniumUtils.ClickOpcion(driver, "form-cabecera:linkindex");
    	Thread.sleep(1000);
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "btLogin", 10);
    	Thread.sleep(1000);
    }
	//	19.	[OpFiltrado] Prueba para el filtrado opcional.
    @Test
    public void t19_OpFiltrado() throws InterruptedException {
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "linklistaviajes", 10);
    	SeleniumUtils.ClickOpcion(driver, "form-cabecera:linklistaviajes");

		//Esperamos que aparezca el elemento filter			
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "class", "ui-column-filter", 2); 
		
		//Pinchamos en primer filtro (salida ciudad)
		elementos.get(0).click();
	
		//Escribimos en el campo tecla a tecla. Eso lo hace builder.sendKeys. (No vale el sendKeys de WebElement)
		Actions builder = new Actions(driver);	    
		builder.sendKeys("n").perform(); // moveToElement(hoverElement).perform();
	
		//Esperar porque se recargue la página el resultado del filtrado
		Thread.sleep(1000);
		
		elementos.get(1).click();
		builder.sendKeys("t").perform();
		Thread.sleep(1000);
	
		//COmo los datos son aleatorios es imposible saber lo que
		//vamos a encontrarnos pero podríamos...
		//Contar el número de filas resultados del filtrado (POr ejemplo  4)
		//tr[contains(@class, 'datatable-selectable')]
		//By busqueda = By.xpath("//tr[contains(@class, 'datatable-selectable')]");
		//campos = driver.findElements(busqueda);			
		//assertTrue("Filtrado incorrecto", campos.size()== 4);		
    }
	//	20.	[OpOrden] Prueba para la ordenación opcional.
    @Test
    public void t20_OpOrden() throws InterruptedException {
    	//Vamos a la vista de filtrado-ordenacion-paginacion
    	testIdentificacionParametros("btLogin", "user6", "user6");
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "misviajes", 10);
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "linklistaviajes", 10);
    	SeleniumUtils.ClickOpcion(driver, "form-cabecera:linklistaviajes");

		//Esperamos que aparezcan los botones de ordenacion
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "class", "sortable-column-icon", 2); 
		
		Thread.sleep(1000); //Esta espera es para poder el efecto de ordenación
		elementos.get(0).click();
		
		//Pinchamos el segundo criterio de ordenacion
		Thread.sleep(1000); //Esta espera es para poder el efecto de ordenación
		elementos.get(1).click();			
		//Pinchamos el cuarto criterio de ordenacion
		Thread.sleep(1000); //Esta espera es para poder el efecto de ordenación
		elementos.get(3).click();
		//Pinchamos el cuarto criterio de ordenacion otra vez
		Thread.sleep(1000); //Esta espera es para poder el efecto de ordenación
		elementos.get(3).click();
		
		Thread.sleep(1000); //Esta espera es para poder el efecto de ordenación
		elementos.get(2).click();
		
		Thread.sleep(1000); //Esta espera es para poder el efecto de ordenación
		elementos.get(4).click();

		//Ahora comprobamos que los datos están ordenados
		Thread.sleep(1000); 
    }
	//	21.	[OpPag] Prueba para la paginación opcional.
    @Test
    public void t21_OpPag() throws InterruptedException {
    	//Vamos a la vista de filtrado-ordenacion-paginacion
    	testIdentificacionParametros("btLogin", "user6", "user6");
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "misviajes", 10);
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "linklistaviajes", 10);
    	SeleniumUtils.ClickOpcion(driver, "form-cabecera:linklistaviajes");
    	//Esperamos que aparezcan los enlaces de paginacion		
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "class", "ui-paginator-next", 2); 
		
		//movemos el raton sobre el botón "siguiente pagina" (el de arriba)
		Actions builder = new Actions(driver);
        builder.moveToElement(elementos.get(0)).perform();   
		//Pinchamos el botón
        elementos.get(0).click();
        Thread.sleep(1500);
		
		//Esperamos de nuevo
      	elementos = SeleniumUtils.EsperaCargaPagina(driver, "class", "ui-paginator-next", 2); 
      	
        //movemos el raton sobre el botón "siguiente pagina" (el de abajo)		
        builder.moveToElement(elementos.get(1)).perform();   
		//Pinchamos el botón
        elementos.get(1).click();

		//Ahora comprobamos que se ha cargado la pagina 3 de 20.
        //elementos = SeleniumUtils.EsperaCargaPagina(driver, "text", "(3 of 20)", 2);
        Thread.sleep(1500);
    }
	//	22.	[OpMante] Prueba del mantenimiento programado opcional.
    @Test  //depende del prueba 13 y 14
    public void t22_OpMante() throws InterruptedException {
    	testIdentificacionParametros("btLogin", "user6", "user6");
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "linkmisviajes", 10);
    	SeleniumUtils.ClickOpcion(driver, "form-cabecera:linkmisviajes");
    	elementos=SeleniumUtils.EsperaCargaPagina(driver, "id", "verViajeRelacionado", 10);
    	elementos.get(1).click();
    	//Vemos que la relacion del usuario con el viaje es SIN_PLAZA
    	SeleniumUtils.EsperaCargaPagina(driver, "id", "btCancelarSolicitud", 10);
    	Thread.sleep(3000);
    }
    
    
}