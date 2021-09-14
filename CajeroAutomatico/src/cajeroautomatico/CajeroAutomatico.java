package cajeroautomatico;

import java.util.Scanner;

/**
 * @author Oscar Sierra
 * Esta clase contiene todas las operaciones y métodos a trabajar sobre los datos
 * de cuenta y sobre los menús de la aplicación.
 */
public class CajeroAutomatico {
    /**
     * Scanner: un scanner universal para ingresar selecciones del usuario.
     */
    static Scanner s=new Scanner(System.in);

    /**
     * Contador universal de cuentas.
     */
    public static int cnt=0,

    /**
     * Variable que tomará el valor de cnt al iniciar sesión como usuario.
     * Esto permitrá trabajar sobre dicha posición en el array.
     */
    num=0;
    
    /**
     * Variable que controla las opciones elegidas por el usuario.
     */
    static int op;

    /**
     * Array para almacenamiento y registro de cuentas.
     */
    public static Cuenta[] cuentas = new Cuenta[100];
    
    /**
     * Clave maestra para el ingreso al menú de administración.
     */
    static int master = 1597;
    
    /**
     * El método main, contiene únicamente el método menu.
     * @param args 
     */
    public static void main(String[] args) {
        menu();
    }
    
    /**
     * Este método contiene las opciones seleccionadas por el usuario al trabajar
     * dentro del menú. Cada opción está controlada bajo un switch, el cual llamará
     * a distintos métodos en base a la selección del usuario.
     */
    public static void menu(){
        System.out.println("----------------------------------------------");
        System.out.println("           --- MENU PRINCIPAL ---");
        System.out.println("1.-Cajero.\n2.-Manejo de cuentas.\n"
                + "3.-Ayuda.\n4.-Salir.");
        System.out.print("\n->Seleccione la opcion que desea: ");
        op=s.nextInt();
        switch(op){
            case 1:
                soliUsuario();
                break;
            case 2:
                int masterp;
                System.out.println("\n----------------------------------------------");
                System.out.print("Ingrese clave maestra: ");
                masterp=s.nextInt();
                if(masterp == 1597){ //valida usuario y contraseña
                    menuCuentas();
                }else{
                    System.out.println("\nLa clave maestra es incorrecta,"
                            + "por favor, intentelo de nuevo.\n");
                    menu();
                }
                menuCuentas();
                break;
            case 3:
                ayuda();
                break;
            case 4: //Finaliza el programa
                System.out.println("--- El programa ha finalizado. ---");
                System.exit(0);
                break;
            default: //Valida la opcion, si no es correcta, inicializa de nuevo el Menu
                System.out.println("--- Ingrese una opcion valida. ---\n");
                menu();
                break;
        }
    }
    
    /**
     * Este método contiene las opciones seleccionadas por el usuario al trabajar
     * dentro del menú del cajero. EL usuario solamente podrá acceder a este menú
     * si fue previamente validado en el método valiUsuario.
     * Cada opción está controlada bajo un switch, el cual llamará
     * a distintos métodos en base a la selección del usuario.
     */
    public static void menuCajero(){
        System.out.println("\n----------------------------------------------");
        System.out.println("  --- Bienvenido " + cuentas[num].getNombre() + "");
        System.out.println("1.-Consultar saldo.\n2.-Retirar.\n"
                + "3.-Cambio de PIN.\n4.-Salir.");
        System.out.print("\n->Seleccione la opcion que desea: ");
        op=s.nextInt();
        switch(op){
            case 1:
                consultaSaldo();
                break;
            case 2:
                Retirar();
                break;
            case 3:
                cambioPIN();
                break;
            case 4:
                menu();
                break;
            default:
                System.out.println("--- Ingrese una opcion valida. ---\n");
                menuCajero();
                break;
        }
    }
    
    /**
     * Este método contiene las opciones seleccionadas por el usuario al trabajar
     * dentro del menú de administración de cuentas. EL administrador solamente podrá acceder a este menú
     * ingresando la clave maestra.
     * Cada opción está controlada bajo un switch, el cual llamará
     * a distintos métodos en base a la selección del usuario.
     */
    public static void menuCuentas(){
        System.out.println("\n----------------------------------------------");
        System.out.println("        --- MENU ADMINISTRACION ---");
        System.out.println("Seleccione la opcion que desea: ");
        System.out.println("1.-Creacion de cuentas.\n2.-Deposito a cuenta.\n"
                + "3.-Retiro a cuenta.\n4.-Modificacion de usuario.\n"
                + "5.-Reporte de saldo en cuenta.\n"
                + "6.-Reporte de balance general.\n7.-Salir");
        System.out.print("\n->Seleccione la opcion que desea: ");
        op=s.nextInt();
        System.out.println("");
        switch(op){
            case 1:
                crearCuenta();
                break;
            case 2:
                deposito();
                break;
            case 3:
                retiro();
                break;
            case 4:
                cambioUsuario();
                break;
            case 5:
                saldoCuenta();
                break;
            case 6:
                balanceGeneral();
                break;
            case 7:
                menu();
                break;
            default:
                System.out.println("--- Ingrese una opcion valida ---");
                menuCuentas();
                break;
        }
    }
    
    /**
     * Este método es utilizado para la creación de nuevas cuentas.
     * Verifica si existen datos ingresados en el array y si es así, verifica si no existen
     * usuarios de cuenta similares al ingresado. Media vez no existan similitudes,
     * solicita los demás datos para el registro en el array. Pedidos los datos, los enví
     * al método nuevaCuenta().
     */
    public static void crearCuenta(){
        Scanner t=new Scanner(System.in);
        System.out.println("\n----------------------------------------------");
        System.out.println("   --------- CREACION DE CUENTAS ---------");
        System.out.println("NOTA: el usuario debe establecerse con numeros y debe ser unico, ej:[1111]\n");
        System.out.print("Ingrese el nuevo usuario: ");
        String usuario = t.nextLine();
        for(int i=0;i<=cnt;i++){
            if(cuentas[i] != null){
                if(usuario.equals(cuentas[i].getUsuario())){ //valida usuario y contraseña
                    System.out.println("----------------------------------------------"
                            + "\nEl usuario " + usuario + " ya existe en el banco, Por favor, intentelo de nuevo.");
                    crearCuenta();
                }
            }else{
                int nC, opn, tp;
                System.out.print("Ingrese la contraseña: ");
                String contra = t.nextLine();
                System.out.print("¿Quien es el titular de la nueva cuenta?: ");
                String nombre = t.nextLine();
                System.out.print("Ingrese el monto de apertura de la cuenta [EN QUETZALES]: Q.");
                float monto = t.nextFloat();
                if(monto == 0){
                    System.out.println("--Usted no puede abrir la cuenta con 0 quetzales.--");
                    System.out.println("Ingrese el monto de apertura de la cuenta: ");
                    monto = t.nextInt();
                }
                int deposito = 0;
                int retiroM = 0;
                int retiroC = 0;
                System.out.print("Seleccione el tipo de cuenta: ");
                System.out.println("\n1.-Ahorro\n2.-Monetaria\n3.-Ahorro programado");
                System.out.print("-> ");
                int tipo = t.nextInt();
                switch(tipo){
                    case 1:
                        tp = 1;
                        nC = cnt;
                        cuentas[cnt] = nuevaCuenta(nC, usuario, contra, nombre, monto, tp, deposito, retiroM, retiroC);
                        cnt++; 
                        System.out.print("----------------------------------------------"
                                + "\nCuenta registrada con exito."
                                + "\n----------------------------------------------"
                                + "\nPresione cualquier numero para continuar: ");
                        opn = s.nextInt();
                        if(opn>=0){
                            menuCuentas();
                        }
                        break;
                    case 2:
                        tp = 2;
                        nC = cnt;
                        cuentas[cnt] = nuevaCuenta(nC, usuario, contra, nombre, monto, tp, deposito, retiroC, retiroM);
                        cnt++; 
                        System.out.print("----------------------------------------------"
                                + "\nCuenta registrada con exito."
                                + "\n----------------------------------------------"
                                + "\nPresione cualquier numero para continuar: ");
                        opn = s.nextInt();
                        if(opn>=0){
                            menuCuentas();
                        }
                        break;
                    case 3:
                        tp = 3;
                        nC = cnt;
                        cuentas[cnt] = nuevaCuenta(nC, usuario, contra, nombre, monto, tp, deposito, retiroC, retiroM);
                        cnt++; 
                        System.out.print("----------------------------------------------"
                                + "\nCuenta registrada con exito."
                                + "\n----------------------------------------------"
                                + "\nPresione cualquier numero para continuar: ");
                        opn = s.nextInt();
                        if(opn>=0){
                            menuCuentas();
                        }
                        break;
                    default:
                        System.out.print("----------------------------------------------"
                                + "\nPor favor, selecciona una opcion valida."
                                + "\n----------------------------------------------"
                                + "\nPresione cualquier numero para continuar: ");
                        opn = s.nextInt();
                        if(opn>=0){
                            crearCuenta();
                        }
                        break;
                }
            }
        }
    }
    
    /**
     * Método que envía los datos de la nueva cuenta al array. Usando los parámetros de la clase Cuenta.
     * @param nC        indica el número de cuenta. Es único, ya que es controlado por la variable
     *                  global cnt, que incrementa al crearse una nueva cuenta.
     * @param usuario   contiene el dato usuario.
     * @param contra    contiene la contraseña para la nueva cuenta.
     * @param nombre    contiene el nombre del titular de la cuenta.
     * @param monto     contiene el monto en quetzales para la apertura de la cuenta.
     * @param tipo      contiene la selección del tipo de cuenta.
     * @param deposito  contiene los depositos realizados.
     * @param retiroC    contiene los retiros realizados por parte del usuario.
     * @param retiroM    contiene los retiros realizados por parte de administración.
     * @return          retorna la nueva cuenta creada.
     */
    public static Cuenta nuevaCuenta(int nC, String usuario, String contra, String nombre,
            float monto, int tipo, int deposito, int retiroM, int retiroC){
        Cuenta cuenta = new Cuenta();
        cuenta.setnC(nC);
        cuenta.setUsuario(usuario);
        cuenta.setContra(contra);
        cuenta.setNombre(nombre);
        cuenta.setMonto(monto);
        cuenta.setTipo(tipo);
        cuenta.setDeposito(deposito);
        cuenta.setRetiroM(retiroM);
        cuenta.setRetiroC(retiroC);
        return cuenta;
    }
    
    /**
     * Este método muestra un cuadro de ayuda al usuario o al administrador.
     */
    public static void ayuda(){
          System.out.println("\n----------------------------------------------");
          System.out.println("               --- AYUDA ---");
          System.out.println("En el menu principal se muestran 4 opciones de navegacion.\n"
                  + "Para ingresar a 'Cajero' debera acceder mediante un usuario y contraseña"
                  + "previamente creados, de igual forma para ingresar a 'Manejo de cuentas'\n"
                  + "necesitara de un usuario y contraseña, ademas de una clave maestra que le\n"
                  + "sera otorgada al momento de crear su cuenta.\n"
                  + "En 'Cajero' encontrara varias opciones, entre las cuales estan:\n"
                  + "-Consultar saldo:\n"
                  + "   En este apartado podra consultar el saldo actual en su cuenta.\n"
                  + "-Retirar:\n"
                  + "   El cajero permitirá el retiro de dinero, el usuario únicamente podrá\n"
                  + "retirar los montos siguientes: Q.50, Q.100, Q.200, Q.300, Q.500, Q.800 y Q.1000.\n"
                  + "Sin embargo, no podra hacer retiros que en su totalidad excedan Q1,500."
                  + "-Cambio de PIN:\n"
                  + "   Podra cambiar su PIN por uno nuevo. Se solicita ingresar su actual"
                  + "pin, seguido de el nuevo que desea.\n"
                  + "-Salir:\n"
                  + "   Regresa al menu principal.\n"
                  + "En 'Manejo de cuentas' encontrara varias opciones, entre las cuales estan:\n"
                  + "-Creacion de cuentas:\n"
                  + "   Podra crear una nueva cuenta.\n"
                  + "-Deposito a cuenta:\n"
                  + "   Permitira realizar un deposito por medio de un numero de cuenta.\n"
                  + "-Retiro a cuenta:\n"
                  + "   Permitira retirar pod medio de un numero de cuenta\n"
                  + "-Modificacion de usuario:\n"
                  + "   Permitira cambiar el PIN de una cuenta especifica.\n"
                  + "-Reporte de saldo en cuenta:\n"
                  + "   Se muestra el saldo en cuenta mediante el numero de cuenta  e hisrorial"
                  + "de transacciones realizadas.\n"
                  + "-Reporte de balance general:\n"
                  + "   Permitira mostrar el saldo del cajero y balance general de todas las cuentas.\n"
                  + "-Salir:\n"
                  + "   Regresa al menu principal.\n\n"
                  + "                   --- ACERCA DE ---\n"
                  + "Version de la aplicacion: 1.0.0 BETA Estable.\n"
                  + "Nombre del modelo: M1906F9SH\n"
                  + "Desarrollador: Oscar Sierra.\n"
                  + "Todos los derechos reservados, prohibida su venta o reproduccion.");
                  System.out.println("----------------------------------------------");
        System.out.print("Presione cualquier numero para continuar: ");
        int opn = s.nextInt();
        if(opn>=0){
            menu();
        }
    }
    
    /**
     * Este método solicita datos de la clase Cuenta para consulta de saldo. Una vez iniciada
     * la sesión de usuario, se solicitan los datos de saldo del array en la posición de la
     * variable global num.
     * @return  retorna la cuenta seleccionada.
     */
    public static Cuenta consultaSaldo(){
        Cuenta cuenta = new Cuenta();
        cuentas[num].getMonto();
        System.out.println("----------------------------------------------");
        System.out.print("Su saldo actual en cuenta es: Q." + cuentas[num].getMonto());
        System.out.println("\n----------------------------------------------");
        System.out.print("Presione cualquier numero para continuar: ");
        int opn = s.nextInt();
        if(opn>=0){
            menuCajero();
        }
        return cuenta;
    }
    
    /**
     * Este método valida los datos del usuario a iniciar sesión. Verifica si existen datos
     * ingresados en el array, y si es así, verifica cada posición hasta encontrar una
     * similitud entre el usuario y contraseña ingresados. Si la verificación es positiva,
     * recurre al método de menuCajero().
     */
    public static void soliUsuario(){
        Scanner u=new Scanner(System.in);
        String usuariop, contrap;
        System.out.println("\n----------------------------------------------");
        System.out.print("Ingrese usuario: ");
        usuariop=u.nextLine();
        System.out.print("Ingrese contraseña: ");
        contrap=u.nextLine();
        for(int i=0;i<=cnt;i++){ //evalua cada posicion en el arreglo de cuentas
            if(cuentas[i] != null){
                if(usuariop.equals(cuentas[i].getUsuario()) && 
                    contrap.equals(cuentas[i].getContra())){ //valida usuario y contraseña
                    num = i;
                    menuCajero();
                }
            }else{
                System.out.println("\nEl usuario o contraseña son incorrectos, o la cuenta no existe en el banco.\n"
                        + "Por favor, intentelo de nuevo.");
                    menu();
            }  
        }
    } 
    
    /**
     * Este método es utilizado para el retiro de fondos controlado por el usuario.
     * Se obtienen todos los datos del usuario que desea retirar fondos, y verifica
     * el tipo de cuenta. Si es de ahorro programado no permitirá al usuario retirar fondos,
     * de no serlo, el usuario podrá seleccionar la cantidad de dinero que desea retirar.
     * opR:     almacena la opción del usuario.
     * ret:     indica el valor de retiro según sea el caso.
     * newR:    almacena el monto del usuario previo al retiro.
     * rewRet:  almacena el monto de newR para luego restarlo con la cantidad establecida
     *          en ret (según sea el caso).
     * Una vez realizado el retiro, se sobreescriben los datos del array en la posición num
     * usando el método nuevaCuenta().
     */
    public static void Retirar(){
        int opR;
        float ret, newR, newRet;
        int nC = cuentas[num].getnC();
        String usuario = cuentas[num].getUsuario();
        String contra = cuentas[num].getContra();
        String nombre = cuentas[num].getNombre();
        int deposito = cuentas[num].getDeposito();
        int retiroM = cuentas[num].getRetiroM();
        int retiroC = cuentas[num].getRetiroC();
        int tipo = cuentas[num].getTipo();
        if(tipo==3){
            System.out.print("Tu cuenta es de ahorro programado, no es posible realizar retiros.\n"
                            + "Presiona cualquier numero para regresar:");
                    int opn = s.nextInt();
                    if(opn>=0){
                        menuCajero();
                    }
        }else{
            System.out.println("\n----------------------------------------------");
            System.out.println("   --------- RETIROS MONETARIOS ---------");
            System.out.println("[1]-Q.50\n[2]-Q.100\n[3]-Q.200\n[4]-Q.300\n[5]-Q.500\n[6]-Q.800\n[7]-Q.1000\n");
            System.out.print("->Seleccione el monto que desea retirar: ");
            opR=s.nextInt();
            switch(opR){
                case 1:
                    ret = 50;
                    newR = cuentas[num].getMonto();
                    newRet = newR;
                    if(newR<ret){
                        regresarRet();
                    }else{
                        newRet -= 50;
                        retiroM++;
                        cuentas[num] = nuevaCuenta(nC, usuario, contra, nombre, newRet, tipo, deposito, retiroM, retiroC);
                        regresarRetReg();
                    }
                    break;
                case 2:
                    ret = 100;
                    newR = cuentas[num].getMonto();
                    newRet = newR;
                    if(newR<ret){
                        regresarRet();
                    }else{
                        newRet -= 100;
                        retiroM++;
                        cuentas[num] = nuevaCuenta(nC, usuario, contra, nombre, newRet, tipo, deposito, retiroM, retiroC);
                        regresarRetReg();
                    };
                    break;
                case 3:
                    ret = 200;
                    newR = cuentas[num].getMonto();
                    newRet = newR;
                    if(newR<ret){
                        regresarRet();
                    }else{
                        newRet -= 200;
                        retiroM++;
                        cuentas[num] = nuevaCuenta(nC, usuario, contra, nombre, newRet, tipo, deposito, retiroM, retiroC);
                        regresarRetReg();
                    }
                    break;
                case 4:
                    ret = 300;
                    newR = cuentas[num].getMonto();
                    newRet = newR;
                    if(newR<ret){
                        regresarRet();
                    }else{
                        newRet -= 300;
                        retiroM++;
                        cuentas[num] = nuevaCuenta(nC, usuario, contra, nombre, newRet, tipo, deposito, retiroM, retiroC);
                        regresarRetReg();
                    }
                    break;
                case 5:
                    ret = 500;
                    newR = cuentas[num].getMonto();
                    newRet = newR;
                    if(newR<ret){
                        regresarRet();
                    }else{
                        newRet -= 500;
                        retiroM++;
                        cuentas[num] = nuevaCuenta(nC, usuario, contra, nombre, newRet, tipo, deposito, retiroM, retiroC);
                        regresarRetReg();
                    }
                    break;
                case 6:
                    ret = 800;
                    newR = cuentas[num].getMonto();
                    newRet = newR;
                    if(newR<ret){
                        regresarRet();
                    }else{
                        newRet -= 800;
                        retiroM++;
                        cuentas[num] = nuevaCuenta(nC, usuario, contra, nombre, newRet, tipo, deposito, retiroM, retiroC);
                        regresarRetReg();
                    }
                    break;
                case 7:
                    ret = 1000;
                    newR = cuentas[num].getMonto();
                    newRet = newR;
                    if(newR<ret){
                        regresarRet();
                    }else{
                        newRet -= 1000;
                        retiroM++;
                        cuentas[num] = nuevaCuenta(nC, usuario, contra, nombre, newRet, tipo, deposito, retiroM, retiroC);
                        regresarRetReg();
                    }
                    break;
                default:
                    System.out.print("----------------------------------------------"
                            + "\nPor favor, selecciona una opcion valida."
                            + "----------------------------------------------\n"
                            + "Presiona cualquier numero para regresar: ");
                    int opn = s.nextInt();
                    if(opn>=0){
                        Retirar();
                    }
                    break;
            }
        }
    }
    
    /**
     * Este método es utilizado para el cambio de PIN controlado por el usuario. 
     * Obtiene los datos de la cuenta a trabajar. Solicita al usuario su PIN actual, y si
     * es verificado mediante el array, pedirá el nuevo PIN de la cuenta. 
     * Una vez realizado el retiro, se sobreescriben los datos del array en la posición num
     * usando el método nuevaCuenta().
     * newPin: almacena el nuevo PIN a registrar.
     */
    public static void cambioPIN(){
        Scanner v=new Scanner(System.in);
        String newCon;
        System.out.println("\n----------------------------------------------");
        System.out.println("      --------- CAMBIO DE PIN ---------");
        System.out.print("Ingrese su PIN actual: ");
        String newC = v.nextLine();
        if(newC.equals(cuentas[num].getContra())){
            int nC = cuentas[num].getnC();
            String usuario = cuentas[num].getUsuario();
            String nombre = cuentas[num].getNombre();
            String con = cuentas[num].getContra();
            float monto = cuentas[num].getMonto();
            int tipo = cuentas[num].getTipo();
            int deposito = cuentas[num].getDeposito();
            int retiroM = cuentas[num].getRetiroM();
            int retiroC = cuentas[num].getRetiroC();
            System.out.print("Ingrese el nuevo PIN: ");
            newCon = v.nextLine();
            cuentas[num] = nuevaCuenta(nC, usuario, newCon, nombre, monto, tipo, deposito, retiroM, retiroC);
            System.out.print("\n----------------------------------------------"
                    + "\nNuevo PIN registrado exitosamente.\n"
                            + "Presiona cualquier numero para regresar: ");
            int opn = s.nextInt();
            if(opn>=0){
                menuCajero();
            }
        }else{
            System.out.print("La contraseña no coincide. Por favor, intentalo de nuevo.\n"
                            + "Presiona cualquier numero para regresar: ");
            int opn = s.nextInt();
            if(opn>=0){
                menuCajero();
            }
        }
    }
    
    /**
     * Este método muestra un mensaje de retiro de efectivo exitoso.
     */
    public static void regresarRetReg(){
        System.out.print("----------------------------------------------"
                            + "\nRetiro realizado exitosamente.\n"
                            + "----------------------------------------------\n"
                            + "Presiona cualquier numero para regresar: ");
        int opn = s.nextInt();
        if(opn>=0){
            menuCajero();
        }
    }
    
    /**
     * Este método muestra un mensaje de insuficiencia de fondos para el retiro.
     */
    public static void regresarRet(){
        System.out.print("----------------------------------------------"
                            + "\nNo posees los fondos suficientes para hacer el retiro."
                            + "----------------------------------------------\n"
                            + "Presiona cualquier numero para regresar: ");
        int opn = s.nextInt();
        if(opn>=0){
            menuCajero();
        }
    }
    
    /**
     * Este método es utilizado para realizar un depósito por parte de administración. 
     * Obtiene los datos de la cuenta a trabajar. Solicita la cuenta de usuario, y si
     * es verificado mediante el array, pedirá el monto a depositar. 
     * Una vez realizado el depósito, se sobreescriben los datos del array en la posición num
     * usando el método nuevaCuenta().
     * dep:     almacena el monto a depositar.
     * newD:    almacena el monto actual de la cuenta.
     * newDep:  almacena la suma del monto actual y el monto a depositar.
     * 
     */
    public static void deposito(){
        Scanner v=new Scanner(System.in);
        String cuenta;
        System.out.println("\n----------------------------------------------");
        System.out.println("  --------- DEPOSITOS MONETARIOS ---------");
        System.out.print("Ingrese la cuenta a la que desea hacer el deposito: ");
        cuenta = v.nextLine();
        for(int i=0;i<=cnt;i++){ //evalua cada posicion en el arreglo de cuentas
            if(cuentas[i] != null){
                if(cuenta.equals(cuentas[i].getUsuario())){ //valida usuario y contraseña
                    float dep, newD, newDep;
                    int nC = cuentas[i].getnC();
                    String usuario = cuentas[i].getUsuario();
                    String contra = cuentas[i].getContra();
                    String nombre = cuentas[i].getNombre();
                    newD = cuentas[i].getMonto();
                    int tipo = cuentas[i].getTipo();
                    int deposito = cuentas[num].getDeposito();
                    int retiroM = cuentas[num].getRetiroM();
                    int retiroC = cuentas[num].getRetiroC();
                    System.out.print("Ingrese el monto a depositar: ");
                    dep = v.nextFloat();
                    newDep = newD += dep;
                    deposito++;
                    cuentas[i] = nuevaCuenta(nC, usuario, contra, nombre, newDep, tipo, deposito, retiroM, retiroC);
                    System.out.print("----------------------------------------------"
                    + "\nDeposito realizado exitosamente."
                            + "\nPresiona cualquier numero para regresar: ");
                    int opn = s.nextInt();
                    if(opn>=0){
                        menuCuentas();
                    }
                }
            }else{
                System.out.print("----------------------------------------------"
                        + "\nLa cuenta no existe en el banco. Por favor, intentelo de nuevo."
                        + "\nPresiona cualquier numero para regresar: ");
                int opn = s.nextInt();
                    if(opn>=0){
                        menuCuentas();
                    }
            }  
        }
    }
    
    /**
     * Este método es utilizado para realizar un retiro por parte de administración. 
     * Obtiene los datos de la cuenta a trabajar. Solicita la cuenta de usuario, y si
     * es verificado mediante el array, pedirá el monto a retirar. 
     * Una vez realizado el retiro, se sobreescriben los datos del array en la posición num
     * usando el método nuevoRetiro().
     */
    public static void retiro(){
        Scanner w=new Scanner(System.in);
        String cuenta;
        System.out.println("\n----------------------------------------------");
        System.out.println("   --------- RETIROS MONETARIOS ---------");
        System.out.print("Ingrese la cuenta a la que desea hacer el retiro: ");
        cuenta = w.nextLine();
        for(int i=0;i<=cnt;i++){ //evalua cada posicion en el arreglo de cuentas
            if(cuentas[i] != null){
                if(cuenta.equals(cuentas[i].getUsuario())){ //valida usuario y contraseña
                    float ret, newRet, newR;
                    int nC = cuentas[i].getnC();
                    String usuario = cuentas[i].getUsuario();
                    String contra = cuentas[i].getContra();
                    String nombre = cuentas[i].getNombre();
                    newR = cuentas[i].getMonto();
                    int tipo = cuentas[i].getTipo();
                    int deposito = cuentas[num].getDeposito();
                    int retiroM = cuentas[num].getRetiroM();
                    int retiroC = cuentas[num].getRetiroC();
                    System.out.print("Ingrese el monto a retirar: ");
                    ret = w.nextFloat();
                    if(ret>newR){
                        System.out.print("----------------------------------------------"
                            + "\nLa cuenta no posee los fondos suficientes para hacer el retiro."
                            + "----------------------------------------------\n"
                            + "Presiona cualquier numero para regresar: ");
                        int opn = s.nextInt();
                        if(opn>=0){
                            menuCuentas();
                        }
                    }else{
                        newRet = newR -= ret;
                        retiroC++;
                        cuentas[i] = nuevaCuenta(nC, usuario, contra, nombre, newRet, tipo, deposito, retiroM, retiroC);
                        System.out.print("----------------------------------------------"
                        + "\nRetiro realizado exitosamente."
                                + "\nPresiona cualquier numero para regresar: ");
                        int opn = s.nextInt();
                        if(opn>=0){
                            menuCuentas();
                        }
                    }
                }
            }else{
                System.out.print("----------------------------------------------"
                        + "\nLa cuenta no existe en el banco. Por favor, intentelo de nuevo."
                        + "\nPresiona cualquier numero para regresar: ");
                int opn = s.nextInt();
                    if(opn>=0){
                        menuCuentas();
                    }
            }  
        }
    }
    
    /**
     * Este método es utilizado para realizar un cambio de PIN por parte de administración. 
     * Obtiene los datos de la cuenta a trabajar. Solicita la cuenta de usuario, y si
     * es verificado mediante el array, pedirá el PIN a modificar. 
     * Una vez realizado el retiro, se sobreescriben los datos del array en la posición num
     * usando el método nuevoPIN().
     */
    public static void cambioUsuario(){
        Scanner v=new Scanner(System.in);
        String cuenta;
        System.out.println("\n----------------------------------------------");
        System.out.println("  --------- MODIFICACION DE USUARIO ---------");
        System.out.print("Ingrese la cuenta que desea modificar: ");
        cuenta = v.nextLine();
        for(int i=0;i<=cnt;i++){ //evalua cada posicion en el arreglo de cuentas
            if(cuentas[i] != null){
                if(cuenta.equals(cuentas[i].getUsuario())){ //valida usuario y contraseña
                    int nC = cuentas[i].getnC();
                    String usuario = cuentas[i].getUsuario();
                    String nombre = cuentas[i].getNombre();
                    float monto = cuentas[i].getMonto();
                    int tipo = cuentas[i].getTipo();
                    int deposito = cuentas[num].getDeposito();
                    int retiroM = cuentas[num].getRetiroM();
                    int retiroC = cuentas[num].getRetiroC();
                    System.out.print("Ingrese el nuevo PIN: ");
                    String newCon = v.nextLine();
                    cuentas[i] = nuevaCuenta(nC, usuario, newCon, nombre, monto, tipo, deposito, retiroM, retiroC);
                    System.out.print("----------------------------------------------"
                            + "\nCambio realizado exitosamente."
                            + "\nPresiona cualquier numero para regresar: ");
                    int opn = s.nextInt();
                    if(opn>=0){
                        menuCuentas();
                    }
                }
            }else{
                System.out.print("----------------------------------------------"
                        + "\nLa cuenta no existe en el banco. Por favor, intentelo de nuevo."
                        + "\nPresiona cualquier numero para regresar: ");
                int opn = s.nextInt();
                    if(opn>=0){
                        menuCuentas();
                    }
            }  
        }
    }
    
    /**
     * Este método muestra el saldo en cuenta de un usuario.
     * Se solicita la cuenta a consultar el saldo, y una vez verificada la cuenta mostrará
     * el monto actual del usuario, los depósitos y retiros realizados a dicha cuenta.
     */
    public static void saldoCuenta(){
        Scanner v=new Scanner(System.in);
        String cuenta;
        System.out.println("\n----------------------------------------------");
        System.out.println("  --------- SALDO EN CUENTA ---------");
        System.out.print("Ingrese la cuenta que desea verificar: ");
        cuenta = v.nextLine();
        for(int i=0;i<=cnt;i++){ //evalua cada posicion en el arreglo de cuentas
            if(cuentas[i] != null){
                if(cuenta.equals(cuentas[i].getUsuario())){
                    System.out.println("----------------------------------------------");
                    System.out.println("Para la cuenta a nombre de: " + cuentas[i].getNombre());
                    System.out.println("Total en cuenta: " + cuentas[i].getMonto());
                    System.out.println("Total depositos: " +  cuentas[i].getDeposito());
                    System.out.println("Total retiros monetarios: " +  cuentas[i].getRetiroM());
                    System.out.println("Total retiros a cuenta: " +  cuentas[i].getRetiroC());
                    System.out.print("----------------------------------------------"
                            + "\nPresiona cualquier numero para regresar: ");
                    int opn = s.nextInt();
                    if(opn>=0){
                        menuCuentas();
                    }
                }
            }else{
                System.out.print("----------------------------------------------"
                        + "\nLa cuenta no existe en el banco. Por favor, intentelo de nuevo."
                        + "\nPresiona cualquier numero para regresar: ");
                int opn = s.nextInt();
                    if(opn>=0){
                        menuCuentas();
                    }
            }  
        }
    }
    
    /**
     * Este método muestra el total de fondos en el cajero, total de depósitos y retiros realizados.
     */
    public static void balanceGeneral(){
        float totMonto=0;
        int totD=0, totRC=0, totRM=0;
        System.out.println("\n----------------------------------------------");
        System.out.println("  --------- BALANCE GENERAL ---------");
        for(int i=0;i<=cnt;i++){
            if(cuentas[i] != null){
                totMonto += cuentas[i].getMonto();
                totD += cuentas[i].getDeposito();
                totRM += cuentas[i].getRetiroM();
                totRC += cuentas[i].getRetiroC();
            }else{
                System.out.println("Total de fondos del cajero: Q." + totMonto);
                System.out.println("Total de depositos: " + totD);
                System.out.println("Total de retiros monetarios: " + totRM);
                System.out.println("Total de retiros a cuenta: " + totRC);
                System.out.print("----------------------------------------------"
                        + "\nPresiona cualquier numero para regresar: ");
                int opn = s.nextInt();
                    if(opn>=0){
                        menuCuentas();
                    }
            }
        }
    }
}