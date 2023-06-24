package ioo.controller;

import ioo.dto.*;
import ioo.model.*;

import java.util.*;

public class Controller {

    private static List<Paciente> pacientes;

    private static List<Sucursal> sucursales;

    private static List<Practica> practicas;
    private static List<Peticion> peticiones;

    private static List<Resultado> resultados;

    private static HashSet<Practica> practicasUsadas = new HashSet<>(); //conjunto de practicas usadas

    private static HashSet<Practica> practicasInhabilitadas = new HashSet<>(); // conjunto de practicas que fueron inhabilitadas para nuevas peticiones
    private static Controller INSTANCE;

    private Controller() {
        initPacientes();
        initPracticas();
        initPeticiones();
        initResultados();
        initSucursales();
    }

    public static synchronized Controller getControlador(){
        if(INSTANCE == null) {
            INSTANCE = new Controller();
        }
        return INSTANCE;
    }

    private static void initPacientes(){
        pacientes = new ArrayList<>();
        pacientes.add(new Paciente(1, "M", 26, 40455964, "Lucas", "Calle falsa", "lucas@gmail.com"));
        pacientes.add(new Paciente(2, "F", 23, 95123456, "Mafe", "Calle Belgrano", "mafe@gmail.com"));
    }

    public boolean crearPaciente(PacienteDTO dto) {
        Paciente pacienteAux = toModelPaciente(dto);
        boolean isExist = false;
        for(int i = 0; i < pacientes.size(); i++) {
            if(pacienteAux.getNumeroPaciente() == pacientes.get(i).getNumeroPaciente()) {
                isExist = true;
            }
        }
        if(!isExist) {
            pacientes.add(pacienteAux);
        } else {
            System.out.println("YA EXISTEEEEEEE.");
            return false;
        }
        return true;
    }

    public void modificarPaciente(int nroPaciente, String sexo, int edad, int dni, String nombre, String domicilio, String mail) {
        int index = getIndexPaciente(nroPaciente);
        if(index != -1){
            pacientes.get(index).setSexo(sexo);
            pacientes.get(index).setEdad(edad);
            pacientes.get(index).setNombre(nombre);
            pacientes.get(index).setDomicilio(domicilio);
            pacientes.get(index).setMail(mail);
        }
    }

    public void eliminarPaciente(Paciente paciente) {
        int index = getIndexPaciente(paciente.getNumeroPaciente());
        if(index != -1){ //encontró el paciente
            if (!pacienteTieneResultados(pacientes.get(index))){ // si no tiene resultados finalizados
                pacientes.remove(index); // elimina el paciente
            } else {
                System.out.print("No puede eliminarse paciente");
            }
        } else {
            System.out.print("Paciente no encontrado");
        }
    }

    private static boolean pacienteTieneResultados (Paciente paciente){
        boolean tieneResultados = false;
        for (int i=0;i<peticiones.size();i++){
            if(peticiones.get(i).getNumeroPaciente().getNumeroPaciente() == paciente.getNumeroPaciente()){ // cuando encuentra una peticion que pertenece al paciente
                for (int j=0;j<peticiones.get(i).getPracticaAsociada().size();j++){ // recorre la lista de prácticas asociadas a esa pertición que pertenece al paciente
                    if (peticiones.get(i).getPracticaAsociada().get(j).getResultado()!= null){ // si esa práctica tiene resultado, devuelve true
                        tieneResultados= true;
                        return tieneResultados;
                    }
                }
            }
        }
        return tieneResultados;
    }

    private int getIndexPaciente(int nroPaciente){
        for (int i=0;i<pacientes.size();i++){
            if(pacientes.get(i).getNumeroPaciente() == nroPaciente){
                return i;
            }
        }
        return -1;
    }

    public static Paciente toModelPaciente(PacienteDTO dto) {
        int nroPaciente = Integer.valueOf(dto.getNumeroPaciente());
        int dni = Integer.valueOf(dto.getDni());
        int edad = Integer.valueOf(dto.getEdad());
        Paciente paciente = new Paciente(nroPaciente, dto.getSexo(), edad, dni, dto.getNombre(), dto.getDomicilio(), dto.getMail());
        return paciente;
    }

    private static void initSucursales(){
        sucursales = new ArrayList<>();
        ArrayList<Paciente> listaPaciente = new ArrayList<>();
        //Creo que la lista de pacientes no va
        listaPaciente.add(new Paciente(1, "M", 26, 40455964, "Lucas", "Calle falsa", "lucas@gmail.com"));
        sucursales.add(new Sucursal(1, "Buenos Aires 123", "Lorena", listaPaciente));
        sucursales.add(new Sucursal(2, "Malvinas 321", "Jhoxani", listaPaciente));
    }

    public void crearSucursal(SucursalDTO sucursal) {
        Sucursal sucursalAux = toModelSucursal(sucursal);
        boolean isExist = false;
        for(int i = 0; i < sucursales.size(); i++) {
            if(sucursalAux.getNroSucursal() == sucursales.get(i).getNroSucursal()) {
                isExist = true;
            }
        }
        if(!isExist) {
            sucursales.add(sucursalAux);
        } else {
            System.out.println("YA EXISTEEEEEEE.");
        }
    }

    public static Sucursal toModelSucursal(SucursalDTO dto) {
        int nroSucursal = Integer.valueOf(dto.getNroSucursal());
        Sucursal sucursal = new Sucursal(nroSucursal, dto.getDireccion(), dto.getResponsableTecnico(), dto.getListaPacientes());
        return sucursal;
    }

    public void modificarSucursal(int nroSucursal, String direccion, String responsableTecnico) {
        int index = getIndexSucursal(nroSucursal);
        if(index != -1){
            sucursales.get(index).setDireccion(direccion);
            sucursales.get(index).setResponsableTecnico(responsableTecnico);
        }
    }

    private static int getIndexSucursal(int nroSucursal){
        for (int i=0;i < sucursales.size();i++){
            if(sucursales.get(i).getNroSucursal() == nroSucursal){
                return i;
            }
        }
        return -1;
    }

    public void eliminarSucursal(Sucursal sucursalAEliminar, Sucursal sucursalADerivar) {
        int index = getIndexSucursal(sucursalAEliminar.getNroSucursal());
        if(index != -1){ //encontró la sucursal
            if (!sucursalTieneResultados(sucursales.get(index))){ // si no tiene resultados finalizados
                derivarSucursal(sucursalAEliminar,sucursalADerivar);
                sucursales.remove(index); // elimina la sucursal
            } else {
                System.out.print("No puede eliminarse la sucursal");
            }
        } else {
            System.out.print("Sucursal no encontrado");
        }
    }

    private static boolean sucursalTieneResultados (Sucursal sucursal){
        boolean tieneResultados = false;
        for (int i=0;i<peticiones.size();i++){
            if(peticiones.get(i).getNumeroSucursal().getNroSucursal() == sucursal.getNroSucursal()){ // cuando encuentra una peticion que pertenece a la sucursal
                for (int j=0;j<peticiones.get(i).getPracticaAsociada().size();j++){ // recorre la lista de prácticas asociadas a esa pertición que pertenece a la sucursal
                    if (peticiones.get(i).getPracticaAsociada().get(j).getResultado()!= null){ // si esa práctica tiene resultado, devuelve true
                        tieneResultados= true;
                        return tieneResultados;
                    }
                }
            }
        }
        return tieneResultados;
    }

    public void derivarSucursal(Sucursal sucursalOrigen, Sucursal sucursalDestino) {
        for (int i=0;i<peticiones.size();i++){
            if(peticiones.get(i).getNumeroSucursal().getNroSucursal() == sucursalOrigen.getNroSucursal()){ // cuando encuentra una peticion que pertenece a la sucursal
                peticiones.get(i).setNumeroSucursal(sucursalDestino); // setea la sucursal como la de destino
            }
        }
    }

    private static void initPracticas(){
        practicas = new ArrayList<>();
        practicas.add(new Practica(0001,"Glucemia","sangre",126, false,72, asignarResultadoAPractica(123))); //ok
        practicas.add(new Practica(0002,"Colesterol","sangre",200,false,72, asignarResultadoAPractica(432))); //critico
        practicas.add(new Practica(0003,"Cloruro","orina",106,false,72, asignarResultadoAPractica(345)));  // ok
        practicas.add(new Practica(0004,"Creatinina","orina",1,false, 72, asignarResultadoAPractica(456))); // ok
    }

    public void crearPractica(PracticaDTO practica) {
        Practica practicaAux = toModelPractica(practica);
        boolean isExist = false;
        for(int i = 0; i < practicas.size(); i++) {
            if(practicaAux.getCodigoPractica() == practicas.get(i).getCodigoPractica()) {
                isExist = true;
            }
        }
        if(!isExist) {
            practicas.add(practicaAux);
        } else {
            System.out.println("YA EXISTEEEEEEE.");
        }
    }

    public static Practica toModelPractica(PracticaDTO dto) {
        int codigoPractica = Integer.valueOf(dto.getCodigoPractica());
        int valoresCriticos = Integer.valueOf(dto.getValoresCriticos());
        int horaParaResultado = Integer.valueOf(dto.getHoraParaResultado());
        Practica practica = new Practica(codigoPractica,dto.getNombre(),dto.getGrupo(),valoresCriticos,dto.isValoresReservados(), horaParaResultado, asignarResultadoAPractica(888));
        return practica;
    }

    public void modificarPractica(int codigoPractica,String nombre, String grupo, int valoresCriticos, int horaParaResultado) {
        int index = getIndexPractica(codigoPractica);
        if(index != -1){
            practicas.get(index).setNombre(nombre);
            practicas.get(index).setGrupo(grupo);
            practicas.get(index).setValoresCriticos(valoresCriticos);
            practicas.get(index).setHoraParaResultado(horaParaResultado);
        }
    }

    private static int getIndexPractica(int nroPractica){
        for (int i=0;i < practicas.size();i++){
            if(practicas.get(i).getCodigoPractica() == nroPractica){
                return i;
            }
        }
        return -1;
    }

    public void eliminarPractica(Practica practica) {
        int index = getIndexPractica(practica.getCodigoPractica());
        if(index != -1){
            if (esPracticaUsada(practica)){
                System.out.print("La práctica ya fue usada así que no puede eliminarse");
            }else {
                practicas.remove(index);
            }
        }
    }

    private static boolean esPracticaUsada (Practica practica) { // busca si la práctica que quiere eliminarse pertenece al conjunto de prácticas usadas
        if (practicasUsadas.contains(practica)) {
            return true;
        }
        return false;
    }

    private static void initPeticiones(){
        peticiones = new ArrayList<>();
        List<Integer> codigosPractica1 = new ArrayList<>();
        codigosPractica1.add(0001);
        codigosPractica1.add(0002);
        List<Integer> codigosPractica2 = new ArrayList<>();
        codigosPractica1.add(0003);
        codigosPractica1.add(0004);
        List<Practica> listaPracticas1 = asignarPracticaAPeticion(codigosPractica1);
        List<Practica> listaPracticas2 = asignarPracticaAPeticion(codigosPractica2);
        peticiones.add(new Peticion(111,buscarPaciente(1),"OSDE",new Date(),listaPracticas1,calcularFechaEntrega(listaPracticas1),buscarSucursal(1)));
        peticiones.add(new Peticion(222,buscarPaciente(2),"SWISS MEDICAL",new Date(),listaPracticas2,calcularFechaEntrega(listaPracticas2),buscarSucursal(2)));
    }

    private static Date calcularFechaEntrega (List<Practica> listaPracticas){
        int suma = 0;
        for(int i = 0; i < listaPracticas.size(); i++) {
            suma+= listaPracticas.get(i).getHoraParaResultado(); // Va sumando las horas que demora cada práctica
        }
        return sumarHorasAFecha(suma);
    }

    private static Date sumarHorasAFecha (int horas) { // suma las horas a la fecha actual en que se creó la petición
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR, horas);
        return calendar.getTime();
    }

    public void crearPeticion(PeticionDTO peticion) {
        Peticion peticionAux = toModelPeticion(peticion);
        boolean isExist = false;
        for(int i = 0; i < peticiones.size(); i++) {
            if(peticionAux.getNumeroPeticion() == peticiones.get(i).getNumeroPeticion()) {
                isExist = true;
            }
        }
        if(!isExist) {
            peticiones.add(peticionAux);
        } else {
            System.out.println("YA EXISTEEEEEEE.");
        }
    }

    public static Peticion toModelPeticion(PeticionDTO dto) {
        int nroPeticion = Integer.valueOf(dto.getNumeroPeticion());
        Peticion peticion = new Peticion(nroPeticion,dto.getNumeroPaciente(),dto.getObraSocial(),dto.getFechaCarga(),dto.getPracticaAsociada(),dto.getFechaEntrega(),dto.getNumeroSucursal());
        return peticion;
    }

    public void modificarPeticion(int nroPeticion, Paciente paciente, String obraSocial, Date fechaCarga, List<Practica> practicaAsociada, Date fechaEntrega,Sucursal sucursal) {
        int index = getIndexPeticion(nroPeticion);
        if(index != -1){
            peticiones.get(index).setNumeroPaciente(paciente);
            peticiones.get(index).setObraSocial(obraSocial);
            peticiones.get(index).setFechaCarga(fechaCarga);
            peticiones.get(index).setPracticaAsociada(practicaAsociada);
            peticiones.get(index).setFechaEntrega(fechaEntrega);
            peticiones.get(index).setNumeroSucursal(sucursal);
        }
    }

    public void eliminarPeticion(Peticion peticion) {
        int index = getIndexPeticion(peticion.getNumeroPeticion());
        if(index != -1){
            peticiones.remove(index);
        }
    }

    private static int getIndexPeticion(int nroPeticion){
        for (int i=0;i < peticiones.size();i++){
            if(peticiones.get(i).getNumeroPeticion() == nroPeticion){
                return i;
            }
        }
        return -1;
    }

    public List<Peticion> listarPeticionesConValoresCriticos () {
        List<Peticion> peticionesConValoresCriticos = null;
        for(int i = 0; i < peticiones.size(); i++) { // recorre todas las peticiones
            for(int j=0; j< peticiones.get(i).getPracticaAsociada().size();j++) { // recorre cada práctica de cada petición
                if (esValorCritico(peticiones.get(i).getPracticaAsociada().get(j).getResultado().getValor(),peticiones.get(i).getPracticaAsociada().get(j).getValoresCriticos())){ // si el resultado de esa práctica es mayor que el valor crítico de la práctica, lo agrega a la lista
                    peticionesConValoresCriticos.add(peticiones.get(i));
                }
            }
        }
        return peticionesConValoresCriticos;
    }

    private static boolean esValorCritico (int valor, int valorCriticoParametro){
        if (valor>valorCriticoParametro){
            return true;
        }
        return false;
    }

    public void mostrarPeticion (Peticion peticion){
        int index = getIndexPeticion(peticion.getNumeroPeticion());
        if(index != -1){ // encontro la peticion
            for(int i=0; i< peticiones.get(index).getPracticaAsociada().size();i++) { // recorre cada práctica de esa peticion
                if (esValorCritico(peticiones.get(i).getPracticaAsociada().get(i).getResultado().getValor(),peticiones.get(i).getPracticaAsociada().get(i).getValoresCriticos())){ // si el resultado de esa práctica es mayor que el valor crítico de la práctica, lo agrega a la lista
                    System.out.print("Retirar por sucursal");
                } else {
                    // ver como se muestra la peticion. tendria que pasar del modelo a la vista
                }
            }
        }
    }

    private static Paciente buscarPaciente(int nroPaciente){
        Paciente pacienteBuscado = null;
        for(int i = 0; i < pacientes.size(); i++) {
            if(nroPaciente == pacientes.get(i).getNumeroPaciente()) {
                pacienteBuscado = pacientes.get(i);
            }
        }
        return pacienteBuscado;
    }

    private static Sucursal buscarSucursal(int nroSucursal){
        Sucursal sucursalBuscada = null;
        for(int i = 0; i < sucursales.size(); i++) {
            if(nroSucursal == sucursales.get(i).getNroSucursal()) {
                sucursalBuscada= sucursales.get(i);
            }
        }
        return sucursalBuscada;
    }

    private static List<Practica> asignarPracticaAPeticion(List<Integer> codigosPractica){ // aca recibe un conjunto de objetos, no un Integer
        List<Practica> practicasAsociadas = null;
        for(int i = 0; i < codigosPractica.size(); i++) {
            for(int k = 0; k < practicas.size(); k++) {
                if(codigosPractica.get(i) == practicas.get(k).getCodigoPractica()) {
                    if (!practicasInhabilitadas.contains(codigosPractica.get(i))){ // si la practica no fue inhabilitada
                        practicasAsociadas.add(practicas.get(k));
                        practicasUsadas.add(practicas.get(k)); // agrega la practica al conjunto practicasUsadas
                    } else {
                        System.out.print("La práctica se encuentra inhabilitada");
                    }
                }
            }
        }
        return practicasAsociadas;
    }

    public void inhabilitarPractica (Practica practica) { //inhabilita una práctica para no poder usarla en futuras peticiones
        practicasInhabilitadas.add(practica);
    }

    private static Resultado asignarResultadoAPractica(int codigoResultado){
        Resultado resultadoAsociado = null;
        for(int i = 0; i < resultados.size(); i++) {
                if(codigoResultado == resultados.get(i).getIdResultado()) {
                    resultadoAsociado = resultados.get(i);
            }
        }
        return resultadoAsociado;
    }

    private static void initResultados() {
        resultados = new ArrayList<>();
        resultados.add(new Resultado(123,new Date(),120)); //glucemia ok
        resultados.add(new Resultado(321,new Date(),130)); //glucemia critico
        resultados.add(new Resultado(234,new Date(),190)); // colesterol ok
        resultados.add(new Resultado(432,new Date(),250)); // colesterol critico
        resultados.add(new Resultado(345,new Date(),100)); // cloruro ok
        resultados.add(new Resultado(543,new Date(),120)); // cloruro critico
        resultados.add(new Resultado(456,new Date(),0)); // creatinina ok
        resultados.add(new Resultado(654,new Date(),2)); // creatinina critico
    }

    public void crearResultado(ResultadoDTO resultado) {
        Resultado resultadoAux = toModelResultado(resultado);
        boolean isExist = false;
        for(int i = 0; i < resultados.size(); i++) {
            if(resultadoAux.getIdResultado() == resultados.get(i).getIdResultado()) {
                isExist = true;
            }
        }
        if(!isExist) {
            resultados.add(resultadoAux);
        } else {
            System.out.println("YA EXISTEEEEEEE.");
        }
    }

    public static Resultado toModelResultado(ResultadoDTO dto) {
        int idResultado = Integer.valueOf(dto.getIdResultado());
        int valor = Integer.valueOf(dto.getValor());
        Resultado resultado = new Resultado(idResultado,dto.getFecha(),valor);
        return resultado;
    }

    public void modificarResultado(int idResultado, Date fecha, int valor) {
        int index = getIndexResultado(idResultado);
        if(index != -1){
            resultados.get(index).setFecha(fecha);
            resultados.get(index).setValor(valor);
        }
    }

    public void eliminarResultado(Resultado resultado) {
        int index = getIndexResultado(resultado.getIdResultado());
        if(index != -1){
            resultados.remove(index);
        }
    }

    private static int getIndexResultado(int nroResultado){
        for (int i=0;i < resultados.size();i++){
            if(resultados.get(i).getIdResultado() == nroResultado){
                return i;
            }
        }
        return -1;
    }
}


