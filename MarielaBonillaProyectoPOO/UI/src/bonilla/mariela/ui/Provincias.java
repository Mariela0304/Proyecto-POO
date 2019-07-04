package bonilla.mariela.ui;

import javafx.collections.*;
import java.util.ArrayList;


public class Provincias {
    ArrayList<ObservableList<String>> itemsCantones = new ArrayList<>() ;
    ArrayList<ObservableList<String>> itemsDistritos = new ArrayList<>();
    ObservableList<String> listaProvincias =
            FXCollections.observableArrayList(
                    "San José", "Alajuela", "Cartago", "Heredia", "Guanacaste", "Puntarenas", "Limón"
            );

    /*
       CANTONES DE PROVINCIAS
    */
    ObservableList<String> itemsSanJose =
            FXCollections.observableArrayList(
                    "San José", "Escazú", "Desamparados", "Puriscal", "Tarrazú", "Aserrí",
                    "Mora", "Goicochea", "Santa Ana", "Alajuelita", "Coronado", "Acosta", "Tibás", "Moravia",
                    "Montes de Oca", "Turrubares", "Dota", "Curridabat", "Pérez Zeledón", "León Cortés"
            );
    ObservableList<String> itemsAlajuela =
            FXCollections.observableArrayList(
                    "Alajuela", "San Ramón", "Grecia", "San Mateo", "Atenas", "Naranjo", "Palamares",
                    "Poás" , "Orotina", "San Carlos", "Zarcero", "Sarchí","Upala", "Los Chiles", "Guatuso",
                    "Río Cuarto"
            );

    ObservableList<String> itemsCartago =
            FXCollections.observableArrayList(
                "Cartago", "Paraíso", "La Unión", "Jiménez", "Turrialba", "Alvarado", "Oreamuno",
                    "El Guarco"
            );

    ObservableList<String> itemsHeredia =
            FXCollections.observableArrayList(
                    "Heredia", "Barva", "Santo Domingo", "Santa Bárbara", "San Rafael", "San Isidro",
                    "Belén", "Flores", "Sarapiquí"
            );

    ObservableList<String> itemsGuanacaste =
            FXCollections.observableArrayList(
                    "Liberia","Nicoya", "Santa Cruz","Bagaces","Carrillo","Cañas", "Abangares",
                    "Tilarán", "Nandayure", "La cruz","Hojancha"
            );

    ObservableList<String> itemsPuntarenas   =
            FXCollections.observableArrayList(

                "Puntarenas", "Esparza", "Buenos Aires", "Montes de Oro", "Osa", "Quepos", "Golfito",
                    "Coto Brus","Parrita", "Corredores", "Garabito"
            );

    ObservableList<String> itemsLimon =
            FXCollections.observableArrayList(
                    "Limón", "Pococí", "Siquírres", "Talamanca", "Matina", "Guácimo"
            );


    /*
    DISTRITOS DE CANTONES
     */

    //Distritos de San José
    ObservableList<String> itemsCantonSanJose =
            FXCollections.observableArrayList(
                    "Carmen", "Merced", "Hospital", "Catedral", "Zapote", "San Francisco de Dos Ríos",
                    "La Uruca", "Mata Redonda", "Pavas", "Hatillo", "San Sebastián"
            );

    ObservableList<String> itemsCantonEscazu =
            FXCollections.observableArrayList(
                    "San Miguel", "San Antonio", "San Rafael"
            );

    ObservableList<String> itemsCantonDesamparados =
            FXCollections.observableArrayList(
                    "Desamparados", "San Miguel", "San Juan de Dios", "San Rafael Arriba", "San Antonio",
                    "Frailes", "Patarrá", "San Cristóbal", "Rosario", "Damas", "San Rafael Abajo", "Gravilias",
                    "Los Guido"
            );

    ObservableList<String> itemsCantonPuriscal =
            FXCollections.observableArrayList(
                    "Santiago", "Mercedes Sur", "Barbacoas", "Grifo Alto", "San Rafael", "Candelarita",
                    "Desamparaditos", "San Antonio", "Chires", "La Cangreja"
            );

    ObservableList<String> itemsCantonTarrazu =
            FXCollections.observableArrayList(
                "San Marcos", "San Lorenzo", "San Carlos"
            );

    ObservableList<String> itemsCantonAserri =
            FXCollections.observableArrayList(
                "Aserrí", "Tarbaca o Pagra", "Vuelta de Jorco", "San Gabriel", "La Legua", "Monterrey", "Salitrillos"
            );

    ObservableList<String> itemsCantonMora =
            FXCollections.observableArrayList(
                "Cuidad Colón", "Guayabo", "Tabarcia", "Piedras Negras", "Picagres", "Jaris",
                    "Quitirrisi"
            );

    ObservableList<String> itemsCantonGoicochea =
            FXCollections.observableArrayList(
                    "Guadalupe", "San Francisco", "Calle Blancos", "Mata de Plátano",
                    "Ipís", "Rancho Redondo", "Purral"
            );

    ObservableList<String> itemsCantonSantaAna =
            FXCollections.observableArrayList(
                "Santa Ana", "Salitral", "Pozos o Concepción", "Uruca o San Joaquín",
                    "Piedades", "Brasil"
            );

    ObservableList<String> itemsCantonAlajuelita =
            FXCollections.observableArrayList(
                "Alajuelita", "San Josecito","San Antonio", "Concepción", "San Felipe"
            );

    ObservableList<String> itemsCantonCoronado =
            FXCollections.observableArrayList(
                "San Isidro", "San Rafael", "Dulce Nombre o Jesús", "Patalillo", "Cascajal"
            );


    ObservableList<String> itemsCantonAcosta =
            FXCollections.observableArrayList(
                "San Ignacio", "Guaitil", "Palmichan", "Cangrejal", "Sabanillas"
            );


    ObservableList<String> itemsCantonTibas =
            FXCollections.observableArrayList(
                "San Juan", "Cinco Esquinas", "Anselmo Llorente", "León XIII", "Colima"
            );


    ObservableList<String> itemsCantonMoravia =
            FXCollections.observableArrayList(
                "San Vicente", "San Jerónimo", "La Trinidad"
            );


    ObservableList<String> itemsCantonMontesDeOca =
            FXCollections.observableArrayList(
                "San Pedro", "Sabanilla", "Mercedes o Betania", "San Rafael"
            );

    ObservableList<String> itemsCantonTurrubares =
            FXCollections.observableArrayList(
                    "San Pablo","San Pedro", "San Juan de Mata", "San Luis",
                    "Carara"
            );

    ObservableList<String> itemsCantonDota =
            FXCollections.observableArrayList(
                    "San María", "Jardín", "Copey"
            );

    ObservableList<String> itemsCantonCurridabat =
            FXCollections.observableArrayList(
                    "Curridabat", "Granadilla", "Sánchez", "Tirrases"
            );


    ObservableList<String> itemsCantonPerezZeledon =
            FXCollections.observableArrayList(
                    "San Isidro de El General", "General", "Daniel Flores", "Rivas",
                    "San Pedro", "platanares", "Pejibaye", "Cajón o Carmen", "Barú", "Río Nuevo",
                    "Páramo", "La Amistad"
            );

    ObservableList<String> itemsCantonLeonCortes =
            FXCollections.observableArrayList(
                    "San Pablo", "San Andrés", "Llano Bonito", "San Isidro", "San Antonio"
            );

    //Distritos de Alajuela


    ObservableList<String> itemsCantonAlajuela =
            FXCollections.observableArrayList(
                "Alajuela", "San José", "Carrizal", "San Antonio", "Guácima", "San Isidro",
                    "Sabanilla", "San Rafael", "Río Segundo", "Desamparados", "Turrucares",
                    "Tambor", "La Garita", "Sarapiquí"
            );

    ObservableList<String> itemsCantonSanRamon =
            FXCollections.observableArrayList(
                    "San Ramón", "Santiago", "San Juan", "Piedades Norte", "Piedades Sur",
                    "San Rafael", "San Isidro", "Angeles", "Alfaro", "Volio", "Concepción", "Zapotal",
                    "San Isidro de Peñas Blancas", "San Lorenzo"
                );

    ObservableList<String> itemsCantonGrecia =
            FXCollections.observableArrayList(
                    "Grecia", "San Isidro", "San José", "San Roque", "Tacares", "Puente Piedra",
                    "Bolívar"

            );

    ObservableList<String> itemsCantonSanMateo =
            FXCollections.observableArrayList(
                    "San Mateo", "Desmonte", "Jesús María", "Labrador"

            );

    ObservableList<String> itemsCantonAtenas =
            FXCollections.observableArrayList(
                "Atenas", "Jesús", "Mercedes", "San Isidro", "Concepción", "San José",
                    "Santa Eulalia", "Escobal"
            );

    ObservableList<String> itemsCantonNaranjo =
            FXCollections.observableArrayList(
                "Naranjo", "San Miguel", "San José", "Cirrí Sur", "San Jerónimo",
                    "San Juan", "Rosario"
            );

    ObservableList<String> itemsCantonPalmares =
            FXCollections.observableArrayList(
                "Palmares", "Zaragosa", "Buenos Aires", "Santiago", "Candelaria",
                    "Esquiipulas", "La Granja"
            );

    ObservableList<String> itemsCantonPoas =
            FXCollections.observableArrayList(
                    "San Pedro", "San Juan", "San Rafael", "Cajarrillos", "Sabana Redonda"

            );

    ObservableList<String> itemsCantonOrotina =
            FXCollections.observableArrayList(
                    "Orotina", "Mastate", "Haciendo Vieja", "Coyolar", "Ceiba"
            );

    ObservableList<String> itemsCantonSanCarlos =
            FXCollections.observableArrayList(
                    "Quesada", "Florencia", "Buena Vista","Aguas Zarcas", "Venecia",
                    "Pital", "Fortuna", "Tigra", "Palmera", "Venado", "Cutris", "Monterrey",
                    "Pocosol"
            );

    ObservableList<String> itemsCantonZarcero =
            FXCollections.observableArrayList(
                "Zarcero","Laguna", "Tapezco", "Guadalupe", "Palmira", "Zapote", "Brisas"
            );

    ObservableList<String> itemsCantonSarchi =
            FXCollections.observableArrayList(
                    "Sarchí Norte", "Sarchí Sur", "Toro Amarillo", "San Pedro", "Rodríguez"
            );

    ObservableList<String> itemsCantonUpala =
            FXCollections.observableArrayList(
                    "Upala", "Aguas Claras", "San José", "Pizote", "Bijagua", "Delicias",
                    "Dos Ríos", "Yolillal", "Canalete"
            );

    ObservableList<String> itemsCantonLosChiles =
            FXCollections.observableArrayList(
                    "Los Chiles", "Caño Negro", "Amparo", "San Jorge"
            );

    ObservableList<String> itemsCantonGuatuso =
            FXCollections.observableArrayList(
                    "San Rafael", "Buenavista", "Cote","Katira"
            );

    ObservableList<String> itemsCantonRioCuarto =
            FXCollections.observableArrayList(
                    "Río Cuarto"
            );


    //Distritos de Cartago
    ObservableList<String> itemsCantonCartago =
            FXCollections.observableArrayList(
                    "Oriental", "Occidental", "Carmen", "Nicolás", "Aguacaliente ((San Francisco)",
                    "Guadalupe  (Arenilla)", "Corralillo", "Tierra Blanca", "Llano Grande", "Quebradilla"
            );
    ObservableList<String> itemsCantonParaiso=
            FXCollections.observableArrayList(
                    "Paraíso", "Santiago", "Orosi", "Cachí", "Llanos de Sta Lucia"
            );
    ObservableList<String> itemsCantonLaUnion =
            FXCollections.observableArrayList(
                    "Tres Ríos", "San Diego", "San Juan", "San Rafael", "Concepción",
                    "Dulce Nombre", "San Ramón", "Río Azul"
            );
    ObservableList<String> itemsCantonJimenez =
            FXCollections.observableArrayList(
                    "Juan Viñas", "Tucurrique", "Pejibaye"
            );
    ObservableList<String> itemsCantonTurrialba =
            FXCollections.observableArrayList(
                    "Turrialba", "La Suiza", "Peralta", "Santa Cruz", "Santa Teresita",
                    "Pavones", "Tuis", "Tayutic", "Santa Rosa", "Tres Equis", "La Isabel", "Chirripó"
            );
    ObservableList<String> itemsCantonAlavarado =
            FXCollections.observableArrayList(
                    "Pecayas", "Cervantes", "Capellades"
            );
    ObservableList<String> itemsCantonOreamuno =
            FXCollections.observableArrayList(
                    "San Rafael", "Cot", "Protrero Cerrado", "Cipreses", "Santa Rosa"
            );


    ObservableList<String> itemsCantonElGuarco =
            FXCollections.observableArrayList(
                    "El Tejar", "San Isidro", "Tobosi", "Patio de Agua"
            );

    //Distritos de Heredia
    ObservableList<String> itemsCantonHeredia =
            FXCollections.observableArrayList(
                    "Heredia", "Mercedes", "San Francisco", "Ulloa", "Vara Blanca"
            );


    ObservableList<String> itemsCantonBarva =
            FXCollections.observableArrayList(
                    "Barva", "San Pedro", "San Pablo", "San Roque", "Santa Lucía",
                    "San José de la Montaña"
            );
    ObservableList<String> itemsCantonSantoDomingo =
            FXCollections.observableArrayList(
                    "Santo Domingo", "San Vicente", "Paracito", "Santo Tomás", "Santa Rosa",
                    "Tures", "Pará"
            );
    ObservableList<String> itemsCantonSantaBarbara =
            FXCollections.observableArrayList(
                    "Santa Bárbara", "San Pedro", "San Juan", "Jesús", "Santo Domingo del Roble",
                    "Puraba"
                    );
    ObservableList<String> itemsCantonSanRafael =
            FXCollections.observableArrayList(
                    "Heredia", "Mercedes", "San Francisco", "Ulloa", "Vara Blanca"
            );
    ObservableList<String> itemsCantonSanIsidro =
            FXCollections.observableArrayList(
                    "Heredia", "Mercedes", "San Francisco", "Ulloa", "Vara Blanca"
            );

    ObservableList<String> itemsCantonBelen =
            FXCollections.observableArrayList(
                    "San Antonio", "La Ribera", "La Asunción"
            );

    ObservableList<String> itemsCantonFlores =
            FXCollections.observableArrayList(
                    "San Joaquín", "Barrantes", "Llorrente"
            );

    ObservableList<String> itemsCantonSanPablo =
            FXCollections.observableArrayList(
                    "San Pablo", "Rincón de Sabanilla"
            );

    ObservableList<String> itemsCantonSarapiqui =
            FXCollections.observableArrayList(
                    "Puerto viejo", "La Virgen", "Horquetas", "Llanuras del Gaspar",
                    "Cureña"
            );


    //Distritos de Guanacaste
    ObservableList<String> itemsCantonLiberia =
            FXCollections.observableArrayList(
                    "Liberia", "Cañas Dulces", "Mayorga", "Nacascolo", "Curubande"
            );


    ObservableList<String> itemsCantonNicoya =
            FXCollections.observableArrayList(
                    "Nicoya", "Mansión", "San Antonio" , "Quebrada Honda", "Sámara",
                    "Nosara", "Belén de Nosarita"
            );

    ObservableList<String> itemsCantonSantaCruz =
            FXCollections.observableArrayList(
                    "Santa Cruz", "Bolsón", "Veintisiete de Abril", "Tempate", "Cartagena",
                    "Cuajiniquil","Diriá", "Cabo Velas", "Tamarindo"
            );
    ObservableList<String> itemsCantonBagaces =
            FXCollections.observableArrayList(
                    "Bagaces", "Fortuna", "Mogote", "Río Naranjo"
            );
    ObservableList<String> itemsCantonCarrillo =
            FXCollections.observableArrayList(
                    "Filadelfia", "Palmira", "Sardinal", "Belén"
            );
    ObservableList<String> itemsCantonCanas =
            FXCollections.observableArrayList(
                    "Cañas", "Palmira", "San Miguel", "Bebedero", "Porozal"
            );
    ObservableList<String> itemsCantonAbangares =
            FXCollections.observableArrayList(
                    "Juntas", "Sierra", "San Juan", "Colorado"
            );
    ObservableList<String> itemsCantonTilaran =
            FXCollections.observableArrayList(
                    "Tilarán", "Quebrada Grande", "Tronadora", "Santa Rosa", "Líbano",
                    "Tierras Morenas", "Arenal"
            );
    ObservableList<String> itemsCantonNandayure =
            FXCollections.observableArrayList(
                    "Carmona", "Santa Rita", "Zapotal", "San Pablo", "Porvenir",
                    "Bejuco"
            );
    ObservableList<String> itemsCantonLaCruz =
            FXCollections.observableArrayList(
                    "La Cruz", "Santa Cecilia", "Garita", "Santa Elena"
            );
    ObservableList<String> itemsCantonHojancha =
            FXCollections.observableArrayList(
                    "Hojancha", "Monte Romo", "Puerto Carrillo", "HuacasLiber", "Matambú"
            );

    //Distritos de Puntarenas
    ObservableList<String> itemsCantonPuntarenas =
            FXCollections.observableArrayList(
                    "Puntarenas", "Pitahaya", "Chomes", "Lepanto", "Paquera", "Manzanillo",
                    "Guacimal", "Barranca", "Monte Verde", "Isla del Coco", "Cóbano", "Chacarita",
                    "Chira (Isla)", "Acapulco", "El Roble", "Arancibia"
            );
    ObservableList<String> itemsCantonEsparza =
            FXCollections.observableArrayList(
                    "Espíritu Santo", "San Juan Grande", "Maracona", "San Rafael", "San Jerónimo",
                    "Caldera"
            );
    ObservableList<String> itemsCantonBuenosAires =
            FXCollections.observableArrayList(
                    "Buenos Aires", "Volcán","Potrero Grande", "Boruca", "Pilas", "Colinas o Bajo de Maíz",
                    "Chánguena", "Biolley", "Brunka"
            );
    ObservableList<String> itemsCantonMonteDeOro =
            FXCollections.observableArrayList(
                    "Miramar", "Unión", "San Isidro"
            );
    ObservableList<String> itemsCantonOsa =
            FXCollections.observableArrayList(
                    "Ciudad Cortés", "Palmar", "Sierpe", "Bahía Ballena", "Piedras Blancas",
                    "Bahpia Drake"
            );
    ObservableList<String> itemsCantonQuepos =
            FXCollections.observableArrayList(
                    "Quepos", "Savegre", "Naranjito"
            );
    ObservableList<String> itemsCantonGolfito =
            FXCollections.observableArrayList(
                    "Golfito", "Puerto Jiménez" ," Guaycará", "Pavones o Villa Conte"
            );
    ObservableList<String> itemsCantonCotoBrus =
            FXCollections.observableArrayList(
                    "San Vito", "Sabalito", "Agua Buena", "Limoncito", "Pittier",
                    "Gutierrez Brown"
            );
    ObservableList<String> itemsCantonParrita =
            FXCollections.observableArrayList(
                    "Parrita"
            );
    ObservableList<String> itemsCantonCorredores =
            FXCollections.observableArrayList(
                    "Corredores", "La Cuesta", "Paso Canoas", "Laurel"
            );
    ObservableList<String> itemsCantonGarabito =
            FXCollections.observableArrayList(
                    "Jacó", "Tárcoles"
            );

    //Distritos de Limón
    ObservableList<String> itemsCantonLimon =
            FXCollections.observableArrayList(
                    "Limón", "Valle La Estrella", "Río Blanco", "Matama"
            );
    ObservableList<String> itemsCantonPococi =
            FXCollections.observableArrayList(
                    "Guápiles", "Jiménez", "Rita","Roxana", "Cariari", "Colorado",
                    "La Colonia"
            );
    ObservableList<String> itemsCantonSiquirres =
            FXCollections.observableArrayList(
                "Siquirres", "Pacuarito", "Florida", "Germania", "Cairo", "Alegría"
            );
    ObservableList<String> itemsCantonTalamanca =
            FXCollections.observableArrayList(
                    "Bratsi", "Sixaola", "Cahuita", "Telire"
            );

    ObservableList<String> itemsCantonMatina =
            FXCollections.observableArrayList(
                    "Matina", "Batán",  "Carrandí"
            );
    ObservableList<String> itemsCantonGuacimo =
            FXCollections.observableArrayList(
                    "Guácimo", "Mercedes", "Pocora", "Río Jiménez",
                    "Duacarí"
            );


    public Provincias() {
        itemsCantones.add(itemsSanJose);
        itemsCantones.add(itemsAlajuela);
        itemsCantones.add(itemsCartago);
        itemsCantones.add(itemsHeredia);
        itemsCantones.add(itemsGuanacaste);
        itemsCantones.add(itemsPuntarenas);
        itemsCantones.add(itemsLimon);

        //Distritos de San José
        itemsDistritos.add(itemsCantonSanJose);
        itemsDistritos.add(itemsCantonEscazu);
        itemsDistritos.add(itemsCantonDesamparados);
        itemsDistritos.add(itemsCantonPuriscal);
        itemsDistritos.add(itemsCantonTarrazu);
        itemsDistritos.add(itemsCantonAserri);
        itemsDistritos.add(itemsCantonMora);
        itemsDistritos.add(itemsCantonGoicochea);
        itemsDistritos.add(itemsCantonSantaAna);
        itemsDistritos.add(itemsCantonAlajuelita);
        itemsDistritos.add(itemsCantonCoronado);
        itemsDistritos.add(itemsCantonAcosta);
        itemsDistritos.add(itemsCantonTibas);
        itemsDistritos.add(itemsCantonMoravia);
        itemsDistritos.add(itemsCantonMontesDeOca);
        itemsDistritos.add(itemsCantonTurrubares);
        itemsDistritos.add(itemsCantonDota);
        itemsDistritos.add(itemsCantonCurridabat);
        itemsDistritos.add(itemsCantonPerezZeledon);
        itemsDistritos.add(itemsCantonLeonCortes);


        //Distritos de Alajuela
        itemsDistritos.add(itemsCantonAlajuela);
        itemsDistritos.add(itemsCantonSanRamon);
        itemsDistritos.add(itemsCantonGrecia);
        itemsDistritos.add(itemsCantonSanMateo);
        itemsDistritos.add(itemsCantonAtenas);
        itemsDistritos.add(itemsCantonNaranjo);
        itemsDistritos.add(itemsCantonPalmares);
        itemsDistritos.add(itemsCantonPoas);
        itemsDistritos.add(itemsCantonOrotina);
        itemsDistritos.add(itemsCantonSanCarlos);
        itemsDistritos.add(itemsCantonZarcero);
        itemsDistritos.add(itemsCantonSarchi);
        itemsDistritos.add(itemsCantonUpala);
        itemsDistritos.add(itemsCantonLosChiles);
        itemsDistritos.add(itemsCantonGuatuso);
        itemsDistritos.add(itemsCantonRioCuarto);

        //Distritos de Cartago
        itemsDistritos.add(itemsCantonCartago);
        itemsDistritos.add(itemsCantonParaiso);
        itemsDistritos.add(itemsCantonLaUnion);
        itemsDistritos.add(itemsCantonJimenez);
        itemsDistritos.add(itemsCantonTurrialba);
        itemsDistritos.add(itemsCantonAlavarado);
        itemsDistritos.add(itemsCantonOreamuno);
        itemsDistritos.add(itemsCantonElGuarco);

        //Distritos de Heredia
        itemsDistritos.add(itemsCantonHeredia);
        itemsDistritos.add(itemsCantonBarva);
        itemsDistritos.add(itemsCantonSantoDomingo);
        itemsDistritos.add(itemsCantonSantaBarbara);
        itemsDistritos.add(itemsCantonSanRafael);
        itemsDistritos.add(itemsCantonSanIsidro);
        itemsDistritos.add(itemsCantonBelen);
        itemsDistritos.add(itemsCantonFlores);
        itemsDistritos.add(itemsCantonSanPablo);
        itemsDistritos.add(itemsCantonFlores);
        itemsDistritos.add(itemsCantonSanPablo);
        itemsDistritos.add(itemsCantonSarapiqui);

        //Distritos de Guanacaste

        itemsDistritos.add(itemsCantonLiberia);
        itemsDistritos.add(itemsCantonNicoya);
        itemsDistritos.add(itemsCantonSantaCruz);
        itemsDistritos.add(itemsCantonBagaces);
        itemsDistritos.add(itemsCantonCarrillo);
        itemsDistritos.add(itemsCantonCanas);
        itemsDistritos.add(itemsCantonAbangares);
        itemsDistritos.add(itemsCantonTilaran);
        itemsDistritos.add(itemsCantonNandayure);
        itemsDistritos.add(itemsCantonLaCruz);
        itemsDistritos.add(itemsCantonHojancha);

        //Distritos de Puntarenas
        itemsDistritos.add(itemsCantonPuntarenas);
        itemsDistritos.add(itemsCantonEsparza);
        itemsDistritos.add(itemsCantonBuenosAires);
        itemsDistritos.add(itemsCantonMonteDeOro);
        itemsDistritos.add(itemsCantonOsa);
        itemsDistritos.add(itemsCantonQuepos);
        itemsDistritos.add(itemsCantonGolfito);
        itemsDistritos.add(itemsCantonCotoBrus);

        itemsDistritos.add(itemsCantonParrita);
        itemsDistritos.add(itemsCantonCorredores);
        itemsDistritos.add(itemsCantonGarabito);

        //Distritos de Limón

        itemsDistritos.add(itemsCantonLimon);
        itemsDistritos.add(itemsCantonPococi);
        itemsDistritos.add(itemsCantonSiquirres);
        itemsDistritos.add(itemsCantonTalamanca);
        itemsDistritos.add(itemsCantonMatina);
        itemsDistritos.add(itemsCantonGuacimo);
//        itemsDistritos.add(itemsCanton);







    }


    public ObservableList<String> getListaProvincias() {
        return listaProvincias;
    }

    public ObservableList<String> getItemsCantones(int index) {
        return itemsCantones.get(index);
    }


    public ObservableList<String> getItemsDistritos(int index) {
        return itemsDistritos.get(index);
    }

    public String getProvincia(int index) {
        return listaProvincias.get(index);
    }

    public ObservableList<String> getCanton(int index) {
        return itemsCantones.get(index);
    }

    public ObservableList<String> getDistrito(int index) {
        return itemsDistritos.get(index);
    }

    public int getTamannoP(){
        return listaProvincias.size();
    }

    public int getTamannoC(){
        return itemsCantones.size();
    }

    public int getTamannoD(){
        return itemsDistritos.size();
    }
}
