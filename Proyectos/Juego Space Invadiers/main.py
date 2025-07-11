import pygame
import random
import math
from pygame import mixer

# Inicializar pygame
pygame.init()

# Crear la pantalla
pantalla = pygame.display.set_mode((800, 600)) #tamaño de pantalla en pixeles

# Titulo e icono de pantalla
pygame.display.set_caption("Invasion Espacial")
icono = pygame.image.load("pinguino.png")
pygame.display.set_icon(icono)
fondo = pygame.image.load("fondo.png")

#Agregar musica
# mixer.music.load("")
# mixer.music.set_volumen(0.5)
# mixer.music.play(-1) #El menos 1 repite cuando acabe

# Jugador
img_jugador = pygame.image.load("nave_espacial.png")
# vairables de posicion inicial del jugador
jugador_x = 368
jugador_y = 500
jugador_x_cambio = 0

# Enemigo
img_enemigo = []
enemigo_x = []
enemigo_y = []
enemigo_x_cambio = []
enemigo_y_cambio = []
cantidad_enemigos = 8

for e in range(cantidad_enemigos):

    # Imagene enemigo
    img_enemigo.append(pygame.image.load("astronave.png"))
    # vairables de posicion inicial del enemigo
    enemigo_x.append(random.randint(0, 736))
    enemigo_y.append(random.randint(50, 200))
    enemigo_x_cambio.append(3)
    enemigo_y_cambio.append(50)



# Disparo
img_bala = pygame.image.load("balas.png")
# vairables de posicion inicial del enemigo
bala_x = 0
bala_y = 500
bala_x_cambio = 0
bala_y_cambio = 8
bala_visible = False

# Puntaje
puntaje = 0
fuente = pygame.font.Font("freesansbold.ttf", 32)
texto_X = 10
texto_y = 10

# texto final juego
fuente_final = pygame.font.Font("freesansbold.ttf", 40)


#funcion texto final del juego
def texto_final():
    mi_fuente_final = fuente_final.render("JUEGO TERMINADO", True, (255, 255, 255))
    pantalla.blit(mi_fuente_final, (60, 200))
    

#Funcion mostrar puntaje
def mostrar_puntaje(x ,y):
    texto = fuente.render(f"Puntaje: {puntaje}", True, (255, 255, 255))
    pantalla.blit(texto, (x, y))

# funcion jugador
def jugador(x, y):
    pantalla.blit(img_jugador, (x, y))
    
    
# funcion enemigo
def enemigo(x, y, ene):
    pantalla.blit(img_enemigo[ene], (x, y))
    
    
# funcion disparar bala
def disparar_bala(x, y):
    global bala_visible
    bala_visible = True
    pantalla.blit(img_bala, (x + 16, y + 10))
    
    
# Funcion detectar coliciones
def hay_colision(x_1, y_1, x_2, y_2):
    distancia = math.sqrt(math.pow(x_1 - x_2, 2) + math.pow(y_2 - y_1, 2))
    if distancia < 27:
        return True
    else:
        return False


# Loop del juego
se_ejecuta = True
while se_ejecuta:
    
    #RGB
    #pantalla.fill((175, 144, 208))
    # Fondo imagen
    pantalla.blit(fondo, (0,0))
    
    # Iterar eventos
    for evento in pygame.event.get():
        
        # Cerrar ventana
        if evento.type == pygame.QUIT: 
            se_ejecuta = False
        
        # Evento para mover al jugador
        if evento.type == pygame.KEYDOWN:
            if evento.key == pygame.K_LEFT:
                jugador_x_cambio = -4
                
            if evento.key == pygame.K_RIGHT:
                jugador_x_cambio = 4
                
            if evento.key == pygame.K_SPACE:
                #sonido_bala = mixer.Sound("")
                #sonido_bala.play()
                if not bala_visible:
                    bala_x = jugador_x
                    disparar_bala(bala_x, bala_y)
                
        
                
        # Evento para dejar de mover al jugador
        if evento.type == pygame.KEYUP:
            if evento.key == pygame.K_LEFT or evento.key == pygame.K_RIGHT:
                jugador_x_cambio = 0
    
    # Modificar el movimiento de cambio del jugador
    jugador_x += jugador_x_cambio
    
    # Mantener dentro de bordes al jugador
    if jugador_x <= 0:
        jugador_x = 0
    elif jugador_x >= 736:
        jugador_x = 736
        
        
    # Modificar el movimiento de cambio del enemigo
    for e in range(cantidad_enemigos):
        
        #Fin juego
        if enemigo_y[e] > 500:
            for k in range(cantidad_enemigos):
                enemigo_y[k] = 1000
            texto_final()
            break
        
        enemigo_x[e] += enemigo_x_cambio[e]
    
        # Mantener dentro de bordes al enemigo
        if enemigo_x[e] <= 0:
            enemigo_x_cambio[e] = 2
            enemigo_y[e] += enemigo_y_cambio[e]
        elif enemigo_x[e] >= 736:
            enemigo_x_cambio[e] = -2
            enemigo_y[e] += enemigo_y_cambio[e]
            
        # Colision
        colision = hay_colision(enemigo_x[e], enemigo_y[e], bala_x, bala_y)
        if colision:
            #sonido_colision = mixer.Sound("")
            #sonido_colision.play()
            bala_y = 500
            bala_visible = False
            puntaje += 1
            enemigo_x[e] = random.randint(0, 736)
            enemigo_y[e] = random.randint(50, 200) 
        
        enemigo(enemigo_x[e], enemigo_y[e], e)
        
    # Movimiento bala
    if bala_y <= -64:
        bala_y = 500
        bala_visible = False
        
        
    if bala_visible:
        disparar_bala(bala_x, bala_y)
        bala_y -= bala_y_cambio
        
     
    jugador(jugador_x, jugador_y)
    
    mostrar_puntaje(texto_X, texto_y)
    
    # Actualizar
    pygame.display.update()