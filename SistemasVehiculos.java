import java.util.*;

class Vehiculo {
    private String marca;
    private String modelo;
    private int velocidadActual;
    private int velocidadMaxima;
    
    public Vehiculo(String marca, String modelo, int velocidadMaxima) {
        this.marca = marca;
        this.modelo = modelo;
        this.velocidadActual = 0;
        this.velocidadMaxima = velocidadMaxima;
    }
    
    public String getMarca() {
        return marca;
    }
    
    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    public String getModelo() {
        return modelo;
    }
    
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    public int getVelocidadActual() {
        return velocidadActual;
    }
    
    protected void setVelocidadActual(int velocidad) {
        if (velocidad >= 0 && velocidad <= velocidadMaxima) {
            this.velocidadActual = velocidad;
        } else if (velocidad > velocidadMaxima) {
            this.velocidadActual = velocidadMaxima;
        } else {
            this.velocidadActual = 0;
        }
    }
    
    public int getVelocidadMaxima() {
        return velocidadMaxima;
    }
    
    public void mostrarInformacion() {
        System.out.println("=== INFORMACION DEL VEHICULO ===");
        System.out.println("Tipo: " + this.getClass().getSimpleName());
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Velocidad actual: " + velocidadActual + " km/h");
        System.out.println("Velocidad maxima: " + velocidadMaxima + " km/h");
        System.out.println("=================================");
    }
    
    public void acelerar() {
        int incremento = 10;
        setVelocidadActual(velocidadActual + incremento);
        System.out.println(marca + " " + modelo + " acelera de manera estandar");
        System.out.println("Velocidad: " + velocidadActual + " km/h (+10 km/h)");
    }
    
    public void frenar() {
        setVelocidadActual(velocidadActual - 15);
        System.out.println(marca + " " + modelo + " esta frenando");
        System.out.println("Velocidad: " + velocidadActual + " km/h");
    }
}

class Auto extends Vehiculo {
    private int numeroPuertas;
    private boolean tieneAireAcondicionado;
    
    public Auto(String marca, String modelo, int velocidadMaxima, int numeroPuertas, boolean tieneAireAcondicionado) {
        super(marca, modelo, velocidadMaxima);
        this.numeroPuertas = numeroPuertas;
        this.tieneAireAcondicionado = tieneAireAcondicionado;
    }
    
    public int getNumeroPuertas() {
        return numeroPuertas;
    }
    
    public boolean isTieneAireAcondicionado() {
        return tieneAireAcondicionado;
    }
    
    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Numero de puertas: " + numeroPuertas);
        System.out.println("Aire acondicionado: " + (tieneAireAcondicionado ? "Si" : "No"));
        System.out.println("=================================");
    }
    
    @Override
    public void acelerar() {
        int incremento = 15;
        setVelocidadActual(getVelocidadActual() + incremento);
        System.out.println(getMarca() + " " + getModelo() + " acelera suavemente");
        System.out.println("Velocidad: " + getVelocidadActual() + " km/h (+15 km/h)");
        
        if (getVelocidadActual() > 80) {
            System.out.println("Velocidad de crucero alcanzada - conduccion estable");
        }
    }
}

class Motocicleta extends Vehiculo {
    private int cilindrada;
    private boolean tieneABS;
    
    public Motocicleta(String marca, String modelo, int velocidadMaxima, int cilindrada, boolean tieneABS) {
        super(marca, modelo, velocidadMaxima);
        this.cilindrada = cilindrada;
        this.tieneABS = tieneABS;
    }
    
    public int getCilindrada() {
        return cilindrada;
    }
    
    public boolean isTieneABS() {
        return tieneABS;
    }
    
    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Cilindrada: " + cilindrada + " cc");
        System.out.println("Sistema ABS: " + (tieneABS ? "Si" : "No"));
        System.out.println("=================================");
    }
    
    @Override
    public void acelerar() {
        int incremento = 25;
        setVelocidadActual(getVelocidadActual() + incremento);
        System.out.println(getMarca() + " " + getModelo() + " acelera rapidamente");
        System.out.println("Velocidad: " + getVelocidadActual() + " km/h (+25 km/h)");
        
        if (getVelocidadActual() > 100) {
            System.out.println("Velocidad deportiva - Cuidado en las curvas");
        }
    }
}

class Camion extends Vehiculo {
    private int capacidadCarga;
    private int numeroEjes;
    
    public Camion(String marca, String modelo, int velocidadMaxima, int capacidadCarga, int numeroEjes) {
        super(marca, modelo, velocidadMaxima);
        this.capacidadCarga = capacidadCarga;
        this.numeroEjes = numeroEjes;
    }
    
    public int getCapacidadCarga() {
        return capacidadCarga;
    }
    
    public int getNumeroEjes() {
        return numeroEjes;
    }
    
    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Capacidad de carga: " + capacidadCarga + " toneladas");
        System.out.println("Numero de ejes: " + numeroEjes);
        System.out.println("=================================");
    }
    
    @Override
    public void acelerar() {
        int incremento = 8;
        setVelocidadActual(getVelocidadActual() + incremento);
        System.out.println(getMarca() + " " + getModelo() + " acelera pesadamente");
        System.out.println("Velocidad: " + getVelocidadActual() + " km/h (+8 km/h)");
        
        if (getVelocidadActual() > 60) {
            System.out.println("Carga pesada - velocidad controlada por seguridad");
        }
    }
}

public class SistemasVehiculos {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE VEHICULOS - DEMOSTRACION DE POLIMORFISMO ===\n");
        
        Vehiculo vehiculoBase = new Vehiculo("Generic", "Base Model", 120);
        Auto auto = new Auto("Chevrolet", "Blazer", 180, 4, true);
        Motocicleta moto = new Motocicleta("Yamaha", "MT-09", 280, 1000, true);
        Camion camion = new Camion("Volvo", "FH16", 90, 40, 3);
        
        List<Vehiculo> vehiculos = new ArrayList<>();
        vehiculos.add(vehiculoBase);
        vehiculos.add(auto);
        vehiculos.add(moto);
        vehiculos.add(camion);
        
        System.out.println("INFORMACION DE TODOS LOS VEHICULOS:\n");
        
        for (int i = 0; i < vehiculos.size(); i++) {
            System.out.println("--- VEHICULO " + (i + 1) + " ---");
            vehiculos.get(i).mostrarInformacion();
            System.out.println();
        }
        
        System.out.println("\nDEMOSTRACION DE POLIMORFISMO - ACELERACION:\n");
        
        for (int i = 0; i < vehiculos.size(); i++) {
            Vehiculo vehiculo = vehiculos.get(i);
            
            System.out.println("--- PRUEBA DE ACELERACION " + (i + 1) + " ---");
            System.out.println("Vehiculo: " + vehiculo.getMarca() + " " + vehiculo.getModelo());
            
            for (int j = 1; j <= 3; j++) {
                System.out.println("\nAceleracion " + j + ":");
                vehiculo.acelerar();
            }
            
            System.out.println("\nFrenando...");
            vehiculo.frenar();
            
            System.out.println("\n" + "=".repeat(50) + "\n");
        }
        
        System.out.println("DEMOSTRACION DE CASTING Y FUNCIONALIDADES ESPECIFICAS:\n");
        
        for (Vehiculo v : vehiculos) {
            if (v instanceof Auto) {
                Auto autoEspecifico = (Auto) v;
                System.out.println("Auto encontrado - Puertas: " + autoEspecifico.getNumeroPuertas());
            } else if (v instanceof Motocicleta) {
                Motocicleta motoEspecifica = (Motocicleta) v;
                System.out.println("Moto encontrada - Cilindrada: " + motoEspecifica.getCilindrada() + "cc");
            } else if (v instanceof Camion) {
                Camion camionEspecifico = (Camion) v;
                System.out.println("Camion encontrado - Carga: " + camionEspecifico.getCapacidadCarga() + " toneladas");
            } else {
                System.out.println("Vehiculo base encontrado");
            }
        }
        
        System.out.println("\nDemostracion de polimorfismo completada!");
        System.out.println("Observa como cada vehiculo acelera de manera diferente a pesar de");
        System.out.println("ser llamado desde la misma lista de tipo 'Vehiculo'.");
    }
}