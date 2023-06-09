package ioo.controller;
import ioo.dto.*;
import ioo.model.*;
import lombok.SneakyThrows;

import java.beans.PropertyEditorSupport;
import java.util.*;

public class Controller {

    private static List<Paciente> pacientes;
    private static List<Sucursal> sucursales;
    private static List<Practica> practicas;
    private static List<Peticion> peticiones;
    private static List<Resultado> resultados;
    private static Peticion peticionAMostrar; // se va a usar en el metodo que muestra una peticion si es que no tiene resultados criticos
    private static HashSet<Practica> practicasUsadas = new HashSet<>(); //conjunto de practicas usadas
    private static HashSet<Practica> practicasInhabilitadas = new HashSet<>(); // conjunto de practicas que fueron inhabilitadas para nuevas peticiones
    private static Controller INSTANCE;


    private Controller() {
        initPacientes();
        initSucursales();
        initResultados();
        initPracticas();
        initPeticiones();
    }

    public static synchronized Controller getControlador(){
        if(INSTANCE == null) {
            INSTANCE = new Controller();
        }
        return INSTANCE;
    }

    private static void initPacientes(){
        pacientes = new ArrayList<>();
        pacientes.add(new Paciente("M", 26, 40455964, "Lucas", "Calle falsa", "lucas@gmail.com"));
        pacientes.add(new Paciente( "F", 23, 95123456, "Mafe", "Calle Belgrano", "mafe@gmail.com"));
    }

    public static Vector<String> getPacientes() {
        Vector<String> pretty_pacients = new Vector<>();
        if (pacientes != null) {
            pretty_pacients.add("DNI             Nombre");
            for(int i = 0; i < pacientes.size(); i++) {
                pretty_pacients.add(pacientes.get(i).getDni() + "   " + pacientes.get(i).getNombre());
            }
        }
        return pretty_pacients;
    }

    @SneakyThrows
    public Vector<String> getPeticionesConResultadoCritico() {
        Vector<String> peticionesConResultadoCritico = new Vector<>();

        peticionesConResultadoCritico.add("Peticiones con resultados criticos, a buscar por sucursal");

        // for loop for resultados
        for(int i = 0; i < resultados.size(); i++) {
            Resultado resultado = resultados.get(i);

            if(resultado.getValor() > practicas.get(resultado.getCodigoPractica()).getValorCritico()) {
                // Este es un resultado critico
                for (int peticion = 0; peticion < peticiones.size(); peticion++) {
                    List<Practica> practicas = peticiones.get(peticion).getPracticasAsociadas();
                    for (int practica = 0; practica < practicas.size(); practica++) {
                        if (practicas.get(practica).getCodigoPractica() == resultado.getCodigoPractica()) {
                            Peticion aux = peticiones.get(peticion);
                            peticionesConResultadoCritico.add("PeticionID: " + String.valueOf(aux.getNumeroPeticion()) + String.valueOf("      Sucursal a retirar: " + aux.getNumeroSucursal().getDireccion()));
                        }
                    }

                }
            }
        }
        return peticionesConResultadoCritico;
    }

    public boolean crearPaciente(PacienteDTO dto) {
        Paciente pacienteAux = toModelPaciente(dto);
        boolean isExist = false;
        for(int i = 0; i < pacientes.size(); i++) {
            if(pacienteAux.getDni() == pacientes.get(i).getDni()) {
                isExist = true;
            }
        }
        if(!isExist) {
            pacientes.add(pacienteAux);
        } else {
            return false;
        }
        return true;
    }

    public boolean modificarPaciente(PacienteDTO dto) {
        Paciente pacienteAux = toModelPaciente(dto);
        int index = getIndexPaciente(pacienteAux.getDni());
        boolean isExist = false;
        if(index != -1){
            pacientes.get(index).setSexo(pacienteAux.getSexo());
            pacientes.get(index).setEdad(pacienteAux.getEdad());
            pacientes.get(index).setNombre(pacienteAux.getNombre());
            pacientes.get(index).setDomicilio(pacienteAux.getDomicilio());
            pacientes.get(index).setMail(pacienteAux.getMail());
            isExist = true;
        }
        return isExist;
    }

    public int eliminarPaciente(EliminarPacienteDTO dto) {
        int index = getIndexPaciente(dto.getNumeroPaciente());
        int retorno = 0;
        if(index != -1) { // encontró el paciente
            if (!pacienteTieneResultados(pacientes.get(index))) { // si no tiene resultados finalizados
                pacientes.remove(index); // elimina el paciente
                retorno = 1; // devuelve 1 si lo eliminó
            } else {
                retorno = 2;// devuelve 2 si lo encontró pero no se encuentra porque tiene resultados
            }
        }
        return retorno; // devuelve 0 si no lo encontró
    }

    private static boolean pacienteTieneResultados (Paciente paciente) {
        boolean tieneResultados = false;
        for (int i=0; i<peticiones.size();i++) {
            if (peticiones.get(i).getNumeroPaciente().getDni()== paciente.getDni()) { // cuando encuentra una peticion que pertenece al paciente
                for (int j=0; j<peticiones.get(i).getPracticasAsociadas().size();j++) {// recorre la lista de prácticas de esa petición
                    for (int k=0; k<resultados.size(); k++){
                        if (resultados.get(k).getCodigoPractica()==peticiones.get(i).getPracticasAsociadas().get(j).getCodigoPractica()) { // compara el codigo de practica del resultado con el codigo de practica de las practicas asociadas a esa peticion
                            tieneResultados =true;
                            return tieneResultados;
                        }
                    }
                }
            }
        }
        return tieneResultados;
    }


    private static int getIndexPaciente(int nroPaciente){
        for (int i=0;i<pacientes.size();i++){
            if(pacientes.get(i).getDni() == nroPaciente){
                return i;
            }
        }
        return -1;
    }

    public static Paciente toModelPaciente(PacienteDTO dto) {
        int nroPaciente = Integer.valueOf(dto.getDni());
        int dni = Integer.valueOf(dto.getDni());
        int edad = Integer.valueOf(dto.getEdad());
        Paciente paciente = new Paciente(dto.getSexo(), edad, dni, dto.getNombre(), dto.getDomicilio(), dto.getMail());
        return paciente;
    }

    private static void initSucursales(){
        sucursales = new ArrayList<>();
        ArrayList<Paciente> listaPaciente = new ArrayList<>();
        //Creo que la lista de pacientes no va
        listaPaciente.add(new Paciente( "M", 26, 40455964, "Lucas", "Calle falsa", "lucas@gmail.com"));
        sucursales.add(new Sucursal(1, "Buenos Aires 123", "Lorena", listaPaciente));
        sucursales.add(new Sucursal(2, "Malvinas 321", "Jhoxani", listaPaciente));
    }

    public boolean crearSucursal(SucursalDTO sucursal) {
        Sucursal sucursalAux = toModelSucursal(sucursal);
        boolean seCreo = true;
        for(int i = 0; i < sucursales.size(); i++) {
            if(sucursalAux.getNroSucursal() == sucursales.get(i).getNroSucursal()) {
                seCreo = false;
                return seCreo;
            }
        }
        sucursales.add(sucursalAux);
        return seCreo;
    }

    public static Sucursal toModelSucursal(SucursalDTO dto) {
        int nroSucursal = Integer.valueOf(dto.getNroSucursal());
        Sucursal sucursal = new Sucursal(nroSucursal, dto.getDireccion(), dto.getResponsableTecnico(), dto.getListaPacientes());
        return sucursal;
    }

    public boolean modificarSucursal(SucursalDTO dto) {
        Sucursal SucursalAux = toModelSucursal(dto);
        int index = getIndexSucursal(SucursalAux.getNroSucursal());
        boolean isExist = false;
        if(index != -1){
            isExist = true;
            sucursales.get(index).setDireccion(SucursalAux.getDireccion());
            sucursales.get(index).setResponsableTecnico(SucursalAux.getResponsableTecnico());
        }
        return isExist;
    }

    private static int getIndexSucursal(int nroSucursal){
        for (int i=0;i < sucursales.size();i++){
            if(sucursales.get(i).getNroSucursal() == nroSucursal){
                return i;
            }
        }
        return -1;
    }

    private static boolean sucursalTieneResultados (Sucursal sucursal) {
        boolean tieneResultados = false;
        for (int i=0; i<peticiones.size();i++) {
            if (peticiones.get(i).getNumeroSucursal().getNroSucursal() == sucursal.getNroSucursal()) { // cuando encuentra una peticion que pertenece a esa sucursal
                for (int j=0; j<peticiones.get(i).getPracticasAsociadas().size();j++) {// recorre la lista de prácticas de esa petición
                    for (int k=0; k<resultados.size(); k++){
                        if (resultados.get(k).getCodigoPractica()==peticiones.get(i).getPracticasAsociadas().get(j).getCU()) { // compara el codigo de practica del resultado con el codigo de practica de las practicas asociadas a esa peticion
                            tieneResultados =true;
                            return tieneResultados;
                        }
                    }
                }
            }
        }
        return tieneResultados;
    }

    public int derivarSucursal(Sucursal sucursalOrigen, Sucursal sucursalDestino) {
        int retorno = 1;
        if(sucursalDestino != null) { //encontró sucursal de destino
            int contador = 0; // cuenta las peticiones que se derivaron
            for (int i=0;i<peticiones.size();i++){
                if(peticiones.get(i).getNumeroSucursal().getNroSucursal() == sucursalOrigen.getNroSucursal()){ // cuando encuentra una peticion que pertenece a la sucursal
                    peticiones.get(i).setNumeroSucursal(sucursalDestino); // setea la sucursal como la de destino
                    contador++; // suma al contador cada peticion derivada
                }
                if (contador >0) {
                    retorno = 2; // si derivo peticiones, retorna 2
                } else {
                    retorno = 3; // si no derivó nada porque la sucursal no tenía peticiones, retorna 3
                }
            }
        }
        return retorno; // retorna 1 si no encontró sucursal destino
    }

    private static void initPracticas(){
        practicas = new ArrayList<>();
        practicas.add(new Practica(1,CUPractica(),"Glucemia","sangre",126, false,72));
        practicas.add(new Practica(2,CUPractica(),"Colesterol","sangre",200,false,72));
        practicas.add(new Practica(3,CUPractica(),"Cloruro","orina",106,false,72));
        practicas.add(new Practica(4,CUPractica(),"Creatinina","orina",1,false, 72));
        practicas.add(new Practica(5,CUPractica(),"HIV","sangre",1,true, 72)); // reservado
    }

    private static int CUPractica(){
        int CUPractica = practicas.size();
        return CUPractica;
    }

    private static Practica crearPractica(int codigo) {
        Practica nuevaPractica = null;
        switch (codigo) {
            case 0001:
                nuevaPractica = new Practica(1,CUPractica(),"Glucemia","sangre",126, false,72);
                break;
            case 0002:
                nuevaPractica = new Practica( 2,CUPractica(),"Colesterol","sangre",200,false,72);
                break;
            case 0003:
                nuevaPractica = new Practica(3,CUPractica(),"Cloruro","orina",106,false,72);
            case 0004:
                nuevaPractica = new Practica(4,CUPractica(),"Creatinina","orina",1,false, 72);
                break;
            case 0005:
                nuevaPractica = new Practica(5,CUPractica(),"HIV","sangre",1,true, 72);
                break;
        }
        return nuevaPractica;

    }

    public static Practica toModelPractica(PracticaDTO dto) {
        int codigoPractica = Integer.valueOf(dto.getCodigoPractica());
        int valoresCriticos = Integer.valueOf(dto.getValoresCriticos());
        int horaParaResultado = Integer.valueOf(dto.getHoraParaResultado());
        int CU = CUPractica();
        Practica practica = new Practica(codigoPractica,CU,dto.getNombre(),dto.getGrupo(),valoresCriticos,dto.isValoresReservados(), horaParaResultado);
        return practica;
    }

    public static Practica toModelEliminarPracticaDTO(EliminarPracticaDTO dto) {
        int codigoPractica = Integer.valueOf(dto.getCodigoPractica());
        Practica practica = null;
        for (int i=0; i < practicas.size(); i++){
            if(practicas.get(i).getCodigoPractica() == codigoPractica){
                practica = new Practica(practicas.get(i).getCodigoPractica(), practicas.get(i).getCU(),practicas.get(i).getNombre(), practicas.get(i).getGrupo(), practicas.get(i).getValorCritico(), practicas.get(i).isValoresReservados(), practicas.get(i).getHoraParaResultado());
            }
        }
        return practica;
    }

    public static Sucursal toModelEliminarSucursalDTO(EliminarSucursalDTO dto) {
        int numeroSucursal = Integer.valueOf(dto.getNumeroSucursal());
        Sucursal sucursal = null;
        for (int i=0; i < sucursales.size(); i++){
            if(sucursales.get(i).getNroSucursal() == numeroSucursal){
                sucursal = new Sucursal(sucursales.get(i).getNroSucursal(), sucursales.get(i).getDireccion(), sucursales.get(i).getResponsableTecnico(), sucursales.get(i).getListaPacientes());
            }
        }
        return sucursal;
    }

    public boolean modificarPractica(PracticaDTO dto) {
        Practica practicaAux = toModelPractica(dto);
        int index = getIndexPractica(practicaAux.getCodigoPractica());
        boolean esModificable = false;
        if(index != -1){
            esModificable = true;
            practicas.get(index).setNombre(practicaAux.getNombre());
            practicas.get(index).setGrupo(practicaAux.getGrupo());
            practicas.get(index).setValorCritico(practicaAux.getValorCritico());
            practicas.get(index).setHoraParaResultado(practicaAux.getHoraParaResultado());
        }
        return esModificable;
    }

    private static int getIndexPractica(int nroPractica){
        for (int i=0;i < practicas.size();i++){
            if(practicas.get(i).getCU() == nroPractica){
                return i;
            }
        }
        return -1;
    }

    public int eliminarPractica(EliminarPracticaDTO dto) {
        int index = getIndexPractica(dto.getCodigoPractica());
        Practica practica = toModelEliminarPracticaDTO(dto);
        int retorno = 0;
        if(index != -1) {
            if (esPracticaUsada(practica)){
                retorno = 1; // retorna 1 si la practica ya fue usada y no puede eliminarse
            }else {
                retorno = 2; // retorna 2 si la practica fue eliminada
                practicas.remove(index);
            }
        }
        return retorno; // retorna 0 si no encontró la practica
    }

    public int eliminarSucursal(EliminarSucursalDTO origenDto, EliminarSucursalDTO destinoDto) {
        Sucursal origen = toModelEliminarSucursalDTO(origenDto);
        Sucursal destino = toModelEliminarSucursalDTO(destinoDto);
        int retorno = 0;
        if(origen != null) {
            int index_origen = getIndexSucursal(origen.getNroSucursal());
            if (!sucursalTieneResultados(sucursales.get(index_origen))){ // si no tiene resultados finalizados
                retorno = derivarSucursal(origen, destino); // retorna 1 si no encontró la sucursal destino, retorna 2 si derivó, retorna 3 si no había peticiones e igual se eliminó
                if (retorno == 2 || retorno == 3) {
                    sucursales.remove(index_origen); // elimina la sucursal
                }
            } else {
                retorno = 4; // retorna 4 si no se puede eliminar la sucursal por tener resultados finalizados
            }
        }
        return retorno; // retorna 0 si no encontró la sucursal origen
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
        codigosPractica1.add(0);
        codigosPractica1.add(1);
        List<Integer> codigosPractica2 = new ArrayList<>();
        codigosPractica2.add(2);
        codigosPractica2.add(3);
        codigosPractica2.add(4);
        codigosPractica2.add(5);
        List<Practica> listaPracticas1 = asignarPracticaAPeticion(codigosPractica1);
        List<Practica> listaPracticas2 = asignarPracticaAPeticion(codigosPractica2);
        peticiones.add(new Peticion(111,buscarPaciente(40455964),"OSDE",new Date(),listaPracticas1,calcularFechaEntrega(listaPracticas1),buscarSucursal(1)));
        peticiones.add(new Peticion(222,buscarPaciente(95123456),"SWISS MEDICAL",new Date(),listaPracticas2,calcularFechaEntrega(listaPracticas2),buscarSucursal(2)));
        peticiones.add(new Peticion(333,buscarPaciente(95123456),"SWISS MEDICAL",new Date(),listaPracticas2,calcularFechaEntrega(listaPracticas2),buscarSucursal(2)));
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

    public Vector<Practica> crearPeticion(PeticionDTO peticion) {
        Peticion peticionAux = toModelPeticion(peticion);
        boolean sePuedeCrear = true;

        Vector<Practica> practicasAsociadas = new Vector<>();

        for(int i = 0; i < peticiones.size(); i++) {
            if(peticionAux.getNumeroPeticion() == peticiones.get(i).getNumeroPeticion()) {
                sePuedeCrear = false;
                break; // Agregar esta línea para salir del bucle cuando se encuentra una coincidencia
            }
        }
        if(sePuedeCrear) {
            peticiones.add(peticionAux);
            for (int i = 0; i < peticionAux.getPracticasAsociadas().size(); i++) {
                practicasAsociadas.add(peticionAux.getPracticasAsociadas().get(i));
            }
        }
        return practicasAsociadas;
    }

    private static Peticion toModelPeticion(PeticionDTO dto) {
        int nroPeticion = Integer.valueOf(dto.getNumeroPeticion());
        int indexPaciente = getIndexPaciente(dto.getNumeroPaciente());
        Paciente paciente = pacientes.get(indexPaciente);
        List<Practica> practicasAsociadas = new ArrayList<>();
        for (int i=0;i<dto.getPracticaAsociada().size();i++){
            Practica practica = crearPractica(dto.getPracticaAsociada().get(i));
            practicas.add(practica);
            practicasAsociadas.add(practica);
        }
        int indexSucursal = getIndexSucursal(dto.getNumeroSucursal());
        Sucursal sucursal = sucursales.get(indexSucursal);
        Peticion peticionNueva = new Peticion(nroPeticion, paciente, dto.getObraSocial(), new Date(), practicasAsociadas, calcularFechaEntrega(practicas), sucursal);
        return peticionNueva;
    }



    public boolean modificarPeticion(int nroPeticion, Paciente paciente, String obraSocial, Date fechaCarga, List<Practica> practicaAsociada, Date fechaEntrega,Sucursal sucursal) {
        int index = getIndexPeticion(nroPeticion);
        boolean esModificable = false;
        if(index != -1){
            esModificable = true;
            peticiones.get(index).setNumeroPaciente(paciente);
            peticiones.get(index).setObraSocial(obraSocial);
            peticiones.get(index).setFechaCarga(fechaCarga);
            peticiones.get(index).setPracticasAsociadas(practicaAsociada);
            peticiones.get(index).setFechaEntrega(fechaEntrega);
            peticiones.get(index).setNumeroSucursal(sucursal);
        }
        return esModificable;
    }

    public boolean eliminarPeticion(EliminarPeticionDTO dto) {
        int index = getIndexPeticion(dto.getnumeroPeticion());
        boolean eseEliminable = false;
        if(index != -1){
            eseEliminable = true;
            peticiones.remove(index);
        }
        return eseEliminable;
    }

    private static int getIndexPeticion(int nroPeticion){
        for (int i=0;i < peticiones.size();i++){
            if(peticiones.get(i).getNumeroPeticion() == nroPeticion){
                return i;
            }
        }
        return -1;
    }

    public List<PeticionMVC> listarPeticionesConValoresCriticos () {
        List<PeticionMVC> peticionesConValoresCriticos = new ArrayList<>();
        for (int i=0; i<peticiones.size();i++) { // recorre todas las peticiones
            boolean bandera = true;
                for (int j=0; j<peticiones.get(i).getPracticasAsociadas().size() && bandera == true ;j++) {// recorre la lista de prácticas de esa petición
                    for (int k=0; k<resultados.size() && bandera == true; k++){ // recorre todos los resultados
                        if (resultados.get(k).getCodigoPractica()==peticiones.get(i).getPracticasAsociadas().get(j).getCodigoPractica()) { // compara el codigo de practica del resultado con el codigo de practica de las practicas asociadas a esa peticion
                            if (esValorCritico(resultados.get(k).getValor(),peticiones.get(i).getPracticasAsociadas().get(j).getValorCritico(), peticiones.get(i).getPracticasAsociadas().get(j).isValoresReservados())) {
                                peticionesConValoresCriticos.add(peticionToVista(peticiones.get(i)));
                                bandera = false; // detiene los for que recorren los resultados y las practicas de esa peticion
                            }
                        }
                    }
                }

            }

        return peticionesConValoresCriticos;
    }

    public PeticionMVC peticionToVista(Peticion peticion) {
        int paciente = peticion.getNumeroPaciente().getDni();
        String nombrePaciente = null;
        for (int i=0; i<pacientes.size();i++){
            if (pacientes.get(i).getDni() == peticion.getNumeroPaciente().getDni() ){
                nombrePaciente = pacientes.get(i).getNombre();
                break;
            }
        }
        List<String> practicas = new ArrayList<>();
        List<String> grupos = new ArrayList<>();
        List<String> resultado = new ArrayList<>();
        for (int i=0; i<peticion.getPracticasAsociadas().size();i++) {
            practicas.add(peticion.getPracticasAsociadas().get(i).getNombre());
            grupos.add(peticion.getPracticasAsociadas().get(i).getGrupo());
            for (int j=0; j<resultados.size();j++){
                if (resultados.get(j).getCodigoPractica() == peticion.getPracticasAsociadas().get(i).getCodigoPractica()) {
                    resultado.add(String.valueOf(resultados.get(j).getValor()));
                }
            }
        }
        PeticionMVC mvc = new PeticionMVC(String.valueOf(peticion.getNumeroPeticion()), String.valueOf(paciente), nombrePaciente,peticion.getObraSocial(), practicas, grupos, String.valueOf(peticion.getNumeroSucursal().getNroSucursal()), resultado);

        return mvc;

    }


    private static boolean esValorCritico (int valor, int valorCriticoParametro, boolean reservado){
        boolean esvalorCritico = false;
        if (valor>valorCriticoParametro || reservado ){
            esvalorCritico = true;
        }
        return esvalorCritico;
    }

    public ResultadoPeticionDTO sePuedeMostrarPeticion(int nroPeticion){
        int index = getIndexPeticion(nroPeticion);
        Vector<Practica> practicasAsociadas = new Vector<>();
        Vector<Resultado> resultadosAsociados = new Vector<>();
        ResultadoPeticionDTO resultadoPeticion = new ResultadoPeticionDTO(0, practicasAsociadas, resultadosAsociados);
        if(index != -1){ // encontro la peticion
            resultadoPeticion.setRetorno(1);
            boolean esCritico = false;
            for (int j=0; j<peticiones.get(index).getPracticasAsociadas().size() && esCritico != true ;j++) {// recorre la lista de prácticas de esa petición
                for (int k=0; k<resultados.size() && esCritico != true; k++){ // recorre todos los resultados
                    if (resultados.get(k).getCodigoPractica() == peticiones.get(index).getPracticasAsociadas().get(j).getCU()) { // compara el codigo de practica del resultado con el codigo de practica de las practicas asociadas a esa peticion
                        resultadoPeticion.getPracticas().add(practicas.get(j));
                        resultadoPeticion.getResultados().add(resultados.get(k));
                        if (esValorCritico(resultados.get(k).getValor(),peticiones.get(index).getPracticasAsociadas().get(j).getValorCritico(), peticiones.get(index).getPracticasAsociadas().get(j).isValoresReservados())) {
                            esCritico = true; // detiene los for que recorren los resultados y las practicas de la peticion
                            resultadoPeticion.setRetorno(2); //retorna 2 si la peticion tiene resultados criticos y no se puede mostrar
                        }
                    }
                }
            }
        }
        int numeroRetorno = resultadoPeticion.getRetorno();
        if (numeroRetorno == 1){
            peticionAMostrar = peticiones.get(index);
        }
        return resultadoPeticion;
    }

    public PeticionMVC mostrarPeticion () { // devuelve a la vista los datos de la peticion una vez que se confirma que se puede mostrar (no tiene resultados con valores reservados o criticos)
        PeticionMVC mvc = peticionToVista(peticionAMostrar);
        peticionAMostrar = null;
        return mvc;
    }

    private static Paciente buscarPaciente(int nroPaciente){
        Paciente pacienteBuscado = null;
        for(int i = 0; i < pacientes.size(); i++) {
            if(nroPaciente == pacientes.get(i).getDni()) {
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
        List<Practica> practicasAsociadas = new ArrayList<>();
        for(int i = 0; i < codigosPractica.size(); i++) {
            for(int k = 0; k < practicas.size(); k++) {
                if(codigosPractica.get(i) == practicas.get(k).getCodigoPractica()) {
                    if (!practicasInhabilitadas.contains(codigosPractica.get(i))){ // si la practica no fue inhabilitada
                        practicasAsociadas.add(practicas.get(k));
                        practicasUsadas.add(practicas.get(k)); // agrega la practica al conjunto practicasUsadas
                    }
                }
            }
        }
        return practicasAsociadas;
    }

    public boolean inhabilitarPractica (Practica practica) { //inhabilita una práctica para no poder usarla en futuras peticiones
        boolean esInhabilitable = false;
        for (int i = 0; i < practicas.size(); i++) {
            if(practica.getCodigoPractica() == practicas.get(i).getCodigoPractica()){
                esInhabilitable = true;
                practicasInhabilitadas.add(practica);
                return esInhabilitable;
            }
        }
        return esInhabilitable;

    }

/*    private static Resultado asignarResultadoAPractica(int codigoResultado){
        Resultado resultadoAsociado = null;
        for(int i = 0; i < resultados.size(); i++) {
            if(codigoResultado == resultados.get(i).getIdResultado()) {
                resultadoAsociado = resultados.get(i);
            }
        }
        return resultadoAsociado;
    }*/

    private static void initResultados() {
        resultados = new ArrayList<>();
        resultados.add(new Resultado(123,0,new Date(),120)); //glucemia ok
        resultados.add(new Resultado(321,1,new Date(),130)); //glucemia critico
        resultados.add(new Resultado(234,2,new Date(),190)); // colesterol ok
        resultados.add(new Resultado(432,3,new Date(),250)); // colesterol critico
        resultados.add(new Resultado(345,4,new Date(),100)); // cloruro ok
        // resultados.add(new Resultado(543,5,new Date(),120)); // cloruro critico
    }

    public boolean crearResultado(ResultadoDTO resultado) { // hacer una comprobacion de que si la practica ya tiene resultado no permita cargar otro
        Resultado resultadoAux = toModelResultado(resultado);
        boolean sePuedeCrear = true;
        for(int i = 0; i < resultados.size(); i++) {
            if(resultadoAux.getCodigoPractica() == resultados.get(i).getCodigoPractica()) {
                sePuedeCrear = false;
            }
        }
        if(sePuedeCrear) {
            resultados.add(resultadoAux);
        }
        return sePuedeCrear;
    }

    public static Resultado toModelResultado(ResultadoDTO dto) {
        int idResultado = Integer.valueOf(CUResultado());
        int codigoPractica = Integer.valueOf((dto.getCodigoPractica()));
        int valor = Integer.valueOf(dto.getValor());
        Resultado resultado = new Resultado(idResultado,codigoPractica,new Date(),valor);
        return resultado;
    }

    private static int CUResultado(){
        int CUResultado = resultados.size();
        return CUResultado;
    }

    public void modificarResultado(int idResultado, int codigoPractica, Date fecha, int valor) {
        int index = getIndexResultado(idResultado);
        if(index != -1){
            resultados.get(index).setCodigoPractica(codigoPractica);
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

    public PacienteMVC mostrarPaciente (int nroPaciente) { // para vista: si el retorno es null, no encontro el paciente
        PacienteMVC mvc = null;
        int index = getIndexPaciente(nroPaciente);
        if (index!= -1) {
            mvc = pacienteToVista(pacientes.get(index));
        }
        return mvc;
    }

   private static PacienteMVC pacienteToVista (Paciente paciente) {
        List<Integer> peticionesDelPaciente = new ArrayList<>();
        for (int i=0;i<peticiones.size();i++){
            if(peticiones.get(i).getNumeroPaciente().getDni()== paciente.getDni()){
                peticionesDelPaciente.add(peticiones.get(i).getNumeroPeticion());
            }
        }
        PacienteMVC mvc = new PacienteMVC(paciente.getSexo(),paciente.getEdad(),paciente.getDni(),paciente.getNombre(),paciente.getDomicilio(),paciente.getMail(),peticionesDelPaciente);
        return mvc;
    }


}


