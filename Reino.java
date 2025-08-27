abstract class Personaje {
    private String nombre;
    private int nivel;
    private String secreto;
    protected int puntosVida;
    protected int energia;
    
    protected Personaje(String nombre, int nivel, String secreto) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.secreto = secreto;
        this.puntosVida = 100;
        this.energia = 50;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public int getNivel() {
        return nivel;
    }
    
    public String revelarSecreto(String claveAcceso) {
        if ("reino_magico".equals(claveAcceso)) {
            return secreto;
        }
        return "El secreto esta oculto";
    }
    
    protected void subirNivel() {
        if (this.nivel < 100) {
            this.nivel++;
            this.puntosVida += 10;
            this.energia += 5;
        }
    }
    
    public abstract void atacar();
    public abstract void habilidadEspecial();
    public abstract void mostrarEstadisticas();
    
    public void descansar() {
        if (energia < 100) {
            energia = Math.min(100, energia + 20);
            System.out.println(nombre + " descansa y recupera energia. Energia actual: " + energia);
        }
    }
}

class Guerrero extends Personaje {
    private int fuerza;
    private String armaFavorita;
    
    public Guerrero(String nombre, int nivel, String secreto, String armaFavorita) {
        super(nombre, nivel, secreto);
        this.fuerza = nivel * 3;
        this.armaFavorita = armaFavorita;
        this.puntosVida += 50;
    }
    
    @Override
    public void atacar() {
        if (energia >= 10) {
            energia -= 10;
            System.out.println(getNombre() + " ataca con " + armaFavorita + " y el daño causado es: " + (fuerza * 2));
                             
            if (Math.random() < 0.1) {
                subirNivel();
                System.out.println(getNombre() + " ha subido de nivel");
            }
        } else {
            System.out.println(getNombre() + " esta demasiado cansado para atacar.");
        }
    }
    
    @Override
    public void habilidadEspecial() {
        if (energia >= 25) {
            energia -= 25;
            System.out.println(getNombre() + " ejecuta un GOLPE DEVASTADOR");
            System.out.println("Su fuerza se duplica temporalmente. Daño: " + (fuerza * 4));
        } else {
            System.out.println(getNombre() + " no tiene suficiente energia para su habilidad especial.");
        }
    }
    
    @Override
    public void mostrarEstadisticas() {
        System.out.println(getNombre() + " - El Guerrero");
        System.out.println("Nivel: " + getNivel());
        System.out.println("Vida: " + puntosVida);
        System.out.println("Energía: " + energia);
        System.out.println("Fuerza: " + fuerza);
        System.out.println("Arma favorita: " + armaFavorita);
    }
}

class Mago extends Personaje {
    private int inteligencia;
    private int mana;
    private String[] hechizos;
    
    public Mago(String nombre, int nivel, String secreto) {
        super(nombre, nivel, secreto);
        this.inteligencia = nivel * 4;
        this.mana = nivel * 10;
        this.hechizos = new String[]{"Bola de Fuego", "Rayo de Hielo", "Teletransporte"};
    }
    
    @Override
    public void atacar() {
        if (mana >= 15) {
            mana -= 15;
            String hechizo = hechizos[(int)(Math.random() * hechizos.length)];
            System.out.println(getNombre() + " lanza " + hechizo + " Daño magico: " + (inteligencia * 2));
                             
            if (Math.random() < 0.15) {
                subirNivel();
                System.out.println(getNombre() + " ha dominado mejor la magia");
            }
        } else {
            System.out.println(getNombre() + " no tiene suficiente mana para lanzar hechizos.");
        }
    }
    
    @Override
    public void habilidadEspecial() {
        if (mana >= 40) {
            mana -= 40;
            System.out.println(getNombre() + " invoca TORMENTA ARCANA");
            System.out.println("Multiples hechizos golpean a los enemigos. Daño: " + (inteligencia * 5));
        } else {
            System.out.println(getNombre() + " necesita mas mana para su habilidad especial.");
        }
    }
    
    @Override
    public void descansar() {
        super.descansar();
        mana = Math.min(100, mana + 30);
        System.out.println("Mana restaurado: " + mana);
    }
    
    @Override
    public void mostrarEstadisticas() {
        System.out.println(getNombre() + " - El Mago");
        System.out.println("Nivel: " + getNivel());
        System.out.println("Vida: " + puntosVida);
        System.out.println("Energía: " + energia);
        System.out.println("Inteligencia: " + inteligencia);
        System.out.println("Mana: " + mana);
        System.out.println("Hechizos conocidos: " + String.join(", ", hechizos));
    }
}

class Explorador extends Personaje {
    private int agilidad;
    private int sigilo;
    private boolean tieneTesoroOculto;
    
    public Explorador(String nombre, int nivel, String secreto) {
        super(nombre, nivel, secreto);
        this.agilidad = nivel * 3;
        this.sigilo = nivel * 2;
        this.tieneTesoroOculto = Math.random() < 0.3;
    }
    
    @Override
    public void atacar() {
        if (energia >= 8) {
            energia -= 8;
            boolean ataquesSigiloso = Math.random() < 0.4;
            
            if (ataquesSigiloso) {
                System.out.println(getNombre() + " ataca desde las sombras! Daño crítico: " + (agilidad * 3));
                                 
            } else {
                System.out.println(getNombre() + " ataca agilmente. Daño: " + (agilidad * 2));
            }
            
            if (Math.random() < 0.12) {
                subirNivel();
                System.out.println(getNombre() + " ha perfeccionado sus tecnicas");
            }
        } else {
            System.out.println(getNombre() + " necesita recuperar energia.");
        }
    }
    
    @Override
    public void habilidadEspecial() {
        if (energia >= 20) {
            energia -= 20;
            System.out.println(getNombre() + " usa GOLPE DE SOMBRA");
            
            if (tieneTesoroOculto) {
                System.out.println("¡Usa un artefacto secreto! Daño explosivo: " + (agilidad * 6));
                tieneTesoroOculto = false;
            } else {
                System.out.println("Ataque desde multiples angulos. Daño: " + (agilidad * 4));
            }
        } else {
            System.out.println(getNombre() + " esta demasiado cansado para tecnicas especiales.");
        }
    }
    
    public void explorar() {
        if (energia >= 15) {
            energia -= 15;
            System.out.println(getNombre() + " explora los alrededores");
            
            double probabilidad = Math.random();
            if (probabilidad < 0.2) {
                tieneTesoroOculto = true;
                System.out.println("¡Ha encontrado un tesoro oculto!");
            } else if (probabilidad < 0.5) {
                energia += 10;
                System.out.println("Encuentra un lugar seguro para descansar.");
            } else {
                System.out.println("No encuentra nada especial esta vez.");
            }
        } else {
            System.out.println(getNombre() + " esta demasiado cansado para explorar.");
        }
    }
    
    @Override
    public void mostrarEstadisticas() {
        System.out.println(getNombre() + " - El Explorador");
        System.out.println("Nivel: " + getNivel());
        System.out.println("Vida: " + puntosVida);
        System.out.println("Energía: " + energia);
        System.out.println("Agilidad: " + agilidad);
        System.out.println("Sigilo: " + sigilo);
        System.out.println("Tesoro oculto: " + (tieneTesoroOculto ? "Sí" : "No"));
    }
}

public class Reino {
    public static void main(String[] args) {
        System.out.println("Bienvenido al Reino de Personajes \n");
        
        Guerrero sergio = new Guerrero("Sergio", 5, "Busca vengar a su clan", "Espada de Fuego");
        Mago duvan = new Mago("Duvan", 4, "Guarda el conocimiento de los antiguos");
        Explorador jersson = new Explorador("Jersson", 6, "Conoce la ubicación del tesoro perdido");
        
        Personaje[] habitantes = {sergio, duvan, jersson};
        
        System.out.println("ESTADISTICAS INICIALES");
        for (Personaje p : habitantes) {
            p.mostrarEstadisticas();
            System.out.println();
        }
        
        System.out.println("COMBATE");
        for (Personaje p : habitantes) {
            p.atacar();
            System.out.println();
        }
        
        System.out.println("HABILIDADES ESPECIALES");
        for (Personaje p : habitantes) {
            p.habilidadEspecial();
            System.out.println();
        }
        
        System.out.println("EXPLORACION");
        if (jersson instanceof Explorador) {
            ((Explorador) jersson).explorar();
        }
        System.out.println();
        
        System.out.println("DESCANSO");
        for (Personaje p : habitantes) {
            p.descansar();
        }
        System.out.println();
        
        System.out.println("SECRETOS DEL REINO");
        System.out.println("Intentando acceder sin clave...");
        for (Personaje p : habitantes) {
            System.out.println(p.getNombre() + ": " + p.revelarSecreto("clave_incorrecta"));
        }
        
        System.out.println("\nUsando la clave correcta");
        for (Personaje p : habitantes) {
            System.out.println(p.getNombre() + ": " + p.revelarSecreto("reino_magico"));
        }
    }
}