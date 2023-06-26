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
        pacientes.add(new Paciente(1, "M", 26, 40455964, "Lucas", "Calle falsa", "lucas@gmail.com"));
        pacientes.add(new Paciente(2, "F", 23, 95123456, "Mafe", "Calle Belgrano", "mafe@gmail.com"));
    }

    public static void getPacientes() {
        for(int i = 0; i < pacientes.size(); i++) {
            System.out.println(pacientes.get(i).getNombre());
        }
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
            return false;
        }
        return true;
    }

    public boolean modificarPaciente(PacienteDTO dto) {
        Paciente pacienteAux = toModelPaciente(dto);
        int index = getIndexPaciente(pacienteAux.getNumeroPaciente());
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

    public boolean eliminarPaciente(EliminarPacienteDTO dto) {
        int index = getIndexPaciente(dto.getNumeroPaciente());
        boolean isExist = false;
        boolean isEliminable = false;
        if(index != -1) { // encontró el paciente
            if (!pacienteTieneResultados(pacientes.get(index))) { // si no tiene resultados finalizados
                pacientes.remove(index); // elimina el paciente
                System.out.print("Se eliminó el paciente");
                isEliminable = true;
                return isEliminable;
            } else {
                System.out.print("No se pudo eliminar porque tiene resultados");
                return isEliminable;
            }
        }
        System.out.print("No existe el paciente");
        return isExist;
    }

    private static boolean pacienteTieneResultados (Paciente paciente) {
        boolean tieneResultados = false;
        for (int i=0; i<peticiones.size();i++) {
            if (peticiones.get(i).getNumeroPaciente().getNumeroPaciente()== paciente.getNumeroPaciente()) { // cuando encuentra una peticion que pertenece al paciente
                for (int j=0; j<peticiones.get(i).getPracticaAsociada().size();j++) {// recorre la lista de prácticas de esa petición
                    for (int k=0; k<resultados.size(); k++){
                        if (resultados.get(k).getCodigoPractica()==peticiones.get(i).getPracticaAsociada().get(j).getCodigoPractica()) { // compara el codigo de practica del resultado con el codigo de practica de las practicas asociadas a esa peticion
                            tieneResultados =true;
                            return tieneResultados;
                        }
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

    public boolean modificarSucursal(int nroSucursal, String direccion, String responsableTecnico) {
        int index = getIndexSucursal(nroSucursal);
        boolean isExist = false;
        if(index != -1){
            isExist = true;
            sucursales.get(index).setDireccion(direccion);
            sucursales.get(index).setResponsableTecnico(responsableTecnico);
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

    public int eliminarSucursal(Sucursal sucursalAEliminar, Sucursal sucursalADerivar) {
        int index = getIndexSucursal(sucursalAEliminar.getNroSucursal());
        int retorno = 0;
        if(index != -1){ //encontró la sucursal
            if (!sucursalTieneResultados(sucursales.get(index))){ // si no tiene resultados finalizados
                retorno = derivarSucursal(sucursalAEliminar,sucursalADerivar); // retorna 1 si no encontró la sucursal destino, retorna 2 si derivó, retorna 3 si no había peticiones e igual se eliminó
                if (retorno == 2 || retorno == 3) {
                    sucursales.remove(index); // elimina la sucursal
                }

            } else {
                retorno = 4; // retorna 4 si no se puede eliminar la sucursal por tener resultados finalizados
                System.out.print("No puede eliminarse la sucursal");
            }
        } else {
            System.out.print("Sucursal no encontrado");
        }
        return retorno; // retorna 0 si no encontró la sucursal origen
    }

    private static boolean sucursalTieneResultados (Sucursal sucursal) {
        boolean tieneResultados = false;
        for (int i=0; i<peticiones.size();i++) {
            if (peticiones.get(i).getNumeroSucursal().getNroSucursal()== sucursal.getNroSucursal()) { // cuando encuentra una peticion que pertenece a esa sucursal
                for (int j=0; j<peticiones.get(i).getPracticaAsociada().size();j++) {// recorre la lista de prácticas de esa petición
                    for (int k=0; k<resultados.size(); k++){
                        if (resultados.get(k).getCodigoPractica()==peticiones.get(i).getPracticaAsociada().get(j).getCodigoPractica()) { // compara el codigo de practica del resultado con el codigo de practica de las practicas asociadas a esa peticion
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
        int index2= getIndexSucursal(sucursalDestino.getNroSucursal());
        int retorno = 1;
        int contador = 0; // cuenta las peticiones que se derivaron
        if(index2 != -1){ //encontró sucursal de destino
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

        } else {
            System.out.print("Sucursal de destino no encontrada");
        }
        return retorno; // retorna 1 si no encontró sucursal destino

    }

    private static void initPracticas(){
        practicas = new ArrayList<>();
        practicas.add(new Practica(0001,"Glucemia","sangre",126, false,72)); //ok
        practicas.add(new Practica(0002,"Colesterol","sangre",200,false,72)); //critico
        practicas.add(new Practica(0003,"Cloruro","orina",106,false,72));  // ok
        practicas.add(new Practica(0004,"Creatinina","orina",1,false, 72)); // ok
    }



    public boolean crearPractica(PracticaDTO practica) {
        Practica practicaAux = toModelPractica(practica);
        boolean seAgrego = true;
        for(int i = 0; i < practicas.size(); i++) {
            if(practicaAux.getCodigoPractica() == practicas.get(i).getCodigoPractica()) {
                seAgrego = false;
                return seAgrego;
            } else {
                practicas.add(practicaAux);
            }
        }
        return seAgrego;

    }

    public static Practica toModelPractica(PracticaDTO dto) {
        int codigoPractica = Integer.valueOf(dto.getCodigoPractica());
        int valoresCriticos = Integer.valueOf(dto.getValoresCriticos());
        int horaParaResultado = Integer.valueOf(dto.getHoraParaResultado());
        Practica practica = new Practica(codigoPractica,dto.getNombre(),dto.getGrupo(),valoresCriticos,dto.isValoresReservados(), horaParaResultado);
        return practica;
    }

    public boolean modificarPractica(int codigoPractica,String nombre, String grupo, int valoresCriticos, int horaParaResultado) {
        int index = getIndexPractica(codigoPractica);
        boolean esModificable = false;
        if(index != -1){
            esModificable = true;
            practicas.get(index).setNombre(nombre);
            practicas.get(index).setGrupo(grupo);
            practicas.get(index).setValoresCriticos(valoresCriticos);
            practicas.get(index).setHoraParaResultado(horaParaResultado);
        }
        return esModificable;
    }

    private static int getIndexPractica(int nroPractica){
        for (int i=0;i < practicas.size();i++){
            if(practicas.get(i).getCodigoPractica() == nroPractica){
                return i;
            }
        }
        return -1;
    }

    public int eliminarPractica(EliminarPracticaDTO codigoPractica) {
        int index = getIndexPractica(codigoPractica.getCodigoPractica());
        int retorno = 0;
        if(index != -1){
            if (esPracticaUsada(codigoPractica)){
                retorno = 1; // retorna 1 si la practica ya fue usada y no puede eliminarse
                System.out.print("La práctica ya fue usada así que no puede eliminarse");
            }else {
                retorno = 2; // retorna 2 si la practica fue eliminada
                practicas.remove(index);
            }
        }
        return retorno; // retorna 0 si no encontró la practica
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

    public boolean crearPeticion(PeticionDTO peticion) {
        Peticion peticionAux = toModelPeticion(peticion);
        boolean sePuedeCrear = true;
        for(int i = 0; i < peticiones.size(); i++) {
            if(peticionAux.getNumeroPeticion() == peticiones.get(i).getNumeroPeticion()) {
                sePuedeCrear = false;
            }
        }
        if(sePuedeCrear) {
            peticiones.add(peticionAux);
        }
        return sePuedeCrear;
    }

    public static Peticion toModelPeticion(PeticionDTO dto) {
        int nroPeticion = Integer.valueOf(dto.getNumeroPeticion());
        Peticion peticion = new Peticion(nroPeticion,dto.getNumeroPaciente(),dto.getObraSocial(),dto.getFechaCarga(),dto.getPracticaAsociada(),dto.getFechaEntrega(),dto.getNumeroSucursal());
        return peticion;
    }

    public boolean modificarPeticion(int nroPeticion, Paciente paciente, String obraSocial, Date fechaCarga, List<Practica> practicaAsociada, Date fechaEntrega,Sucursal sucursal) {
        int index = getIndexPeticion(nroPeticion);
        boolean esModificable = false;
        if(index != -1){
            esModificable = true;
            peticiones.get(index).setNumeroPaciente(paciente);
            peticiones.get(index).setObraSocial(obraSocial);
            peticiones.get(index).setFechaCarga(fechaCarga);
            peticiones.get(index).setPracticaAsociada(practicaAsociada);
            peticiones.get(index).setFechaEntrega(fechaEntrega);
            peticiones.get(index).setNumeroSucursal(sucursal);
        }
        return esModificable;
    }

    public boolean eliminarPeticion(EliminarPeticionDTO peticion) {
        int index = getIndexPeticion(peticion.getnumeroPeticion());
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

    public List<Peticion> listarPeticionesConValoresCriticos () {
        List<Peticion> peticionesConValoresCriticos = new ArrayList<>();
        for (int i=0; i<peticiones.size();i++) { // recorre todas las peticiones
            boolean bandera = true;
                for (int j=0; j<peticiones.get(i).getPracticaAsociada().size() && bandera == true ;j++) {// recorre la lista de prácticas de esa petición
                    for (int k=0; k<resultados.size() && bandera == true; k++){ // recorre todos los resultados
                        if (resultados.get(k).getCodigoPractica()==peticiones.get(i).getPracticaAsociada().get(j).getCodigoPractica()) { // compara el codigo de practica del resultado con el codigo de practica de las practicas asociadas a esa peticion
                            if (esValorCritico(resultados.get(k).getValor(),peticiones.get(i).getPracticaAsociada().get(j).getValoresCriticos())) {
                                peticionesConValoresCriticos.add(peticiones.get(i));
                                bandera = false; // detiene los for que recorren los resultados y las practicas de esa peticion
                            }
                        }
                    }
                }

            }


        return peticionesConValoresCriticos;
    }


    private static boolean esValorCritico (int valor, int valorCriticoParametro){
        boolean esvalorCritico = false;
        if (valor>valorCriticoParametro){
            esvalorCritico = true;
        }
        return esvalorCritico;
    }

    public int mostrarPeticion (Peticion peticion){ // ver como hacer porque tiene que retornar la peticion o el mensaje de que no se puede mostrar. REHACER CON LA NUEVA ESTRUCTURA DE RESULTADO
        int index = getIndexPeticion(peticion.getNumeroPeticion());
        int retorno = 0;
        if(index != -1){ // encontro la peticion
            retorno = 1; // retorna 1 si la peticion se puede mostrar
            boolean bandera = true;
               for (int j=0; j<peticiones.get(index).getPracticaAsociada().size() && bandera == true ;j++) {// recorre la lista de prácticas de esa petición
                   for (int k=0; k<resultados.size() && bandera == true; k++){ // recorre todos los resultados
                       if (resultados.get(k).getCodigoPractica()==peticiones.get(index).getPracticaAsociada().get(j).getCodigoPractica()) { // compara el codigo de practica del resultado con el codigo de practica de las practicas asociadas a esa peticion
                           if (esValorCritico(resultados.get(k).getValor(),peticiones.get(index).getPracticaAsociada().get(j).getValoresCriticos())) {
                               bandera = false; // detiene los for que recorren los resultados y las practicas de la peticion
                               retorno = 2; //retorna 2 si la peticion tiene resultados criticos y no se puede mostrar
                            }
                        }
                    }
                }

            }

        return retorno;
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
            System.out.print(sucursales.get(i).getNroSucursal());
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
                    } else {
                        System.out.print("La práctica se encuentra inhabilitada");
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
        resultados.add(new Resultado(123,001,new Date(),120)); //glucemia ok
        resultados.add(new Resultado(321,001,new Date(),130)); //glucemia critico
        resultados.add(new Resultado(234,002,new Date(),190)); // colesterol ok
        resultados.add(new Resultado(432,002,new Date(),250)); // colesterol critico
        resultados.add(new Resultado(345,003,new Date(),100)); // cloruro ok
        resultados.add(new Resultado(543,003,new Date(),120)); // cloruro critico
        resultados.add(new Resultado(456,004,new Date(),0)); // creatinina ok
        resultados.add(new Resultado(654,004,new Date(),2)); // creatinina critico
    }

    public boolean crearResultado(ResultadoDTO resultado) { // hacer una comprobacion de que si la practica ya tiene resultado no permita cargar otro
        Resultado resultadoAux = toModelResultado(resultado);
        boolean sePuedeCrear = true;
        boolean isExist = false;
        for(int i = 0; i < resultados.size(); i++) {
            if(resultadoAux.getIdResultado() == resultados.get(i).getIdResultado()) {
                sePuedeCrear = false;
            }
        }
        if(sePuedeCrear) {
            resultados.add(resultadoAux);
        }
        return sePuedeCrear;
    }

    public static Resultado toModelResultado(ResultadoDTO dto) {
        int idResultado = Integer.valueOf(dto.getIdResultado());
        int codigoPractica = Integer.valueOf((dto.getCodigoPractica()));
        int valor = Integer.valueOf(dto.getValor());
        Resultado resultado = new Resultado(idResultado,codigoPractica,dto.getFecha(),valor);
        return resultado;
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
}


