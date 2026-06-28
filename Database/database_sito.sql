DROP DATABASE IF EXISTS e_commerce_tsw;
CREATE DATABASE e_commerce_tsw;
USE e_commerce_tsw;


-- UTENTE

CREATE TABLE IF NOT EXISTS utente (
    id INT auto_increment PRIMARY KEY ,
    tipo_utente TINYINT NOT NULL DEFAULT 3 CHECK(
    tipo_utente IN (1,2,3) 
    ), -- 1=admin, 2=staff, 3=cliente (default)
    codice_utente CHAR(6) NOT NULL UNIQUE CHECK(
    codice_utente REGEXP '^[A-Z][0-9]{5}$'),
    stato_account TINYINT NOT NULL DEFAULT 3 CHECK(
    stato_account IN(1,2,3) 
    ), -- 1=attivo, 2=inattivo/sospeso, 3=in attesa di verifica.
    nome VARCHAR(100) NOT NULL CHECK(
    LENGTH(nome)>=3
	),
    cognome VARCHAR(100) NOT NULL CHECK(
    LENGTH(cognome)>=2
    ),
    data_nascita DATE NOT NULL CHECK(
    data_nascita >= '1900-01-01'
    ),
    numero_telefono VARCHAR(13) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE, 
    password_u VARCHAR(255) NOT NULL 
    );
    
-- UTENTE

CREATE TABLE IF NOT EXISTS categoria(
id INT auto_increment PRIMARY KEY UNIQUE,

nome_categoria VARCHAR(100) NOT NULL UNIQUE
);

-- PRODOTTO

CREATE TABLE IF NOT EXISTS prodotto(
id INT auto_increment PRIMARY KEY UNIQUE,
nome_prodotto VARCHAR(100) NOT NULL ,
descrizione TEXT, -- per le descrizioni

prezzo DECIMAL(10,2) NOT NULL CHECK(prezzo>=0), -- decimal max prezzo con 10 cifre e 2 dopo la virgola
iva DECIMAL(4,2) NOT NULL DEFAULT 22.00,
quantita_disponibile INT NOT NULL DEFAULT 0 CHECK(quantita_disponibile>=0),
id_categoria INT NOT NULL,
FOREIGN KEY (id_categoria) REFERENCES categoria(id) -- foreign key il legame tra le 2 tabelle e reference a quale tabella punta in questo caso in id della tabella categoria
ON UPDATE CASCADE --  modifica in automatico tutti gli id in caso di cambiamento dall'admin
ON DELETE RESTRICT -- impedisce la cancellazione in caso ci siano ancora elementi dentro quella categoria

);

-- CARRELLO

CREATE TABLE IF NOT EXISTS carrello(
id INT auto_increment PRIMARY KEY UNIQUE,

id_utente INT NOT NULL UNIQUE, -- un solo carrello per utente

FOREIGN KEY(id_utente) REFERENCES utente(id) -- crea il legame tra tabella carrello e utente e capisce se esiste l'utente
ON UPDATE CASCADE -- per cambiare l'id automaticamente in caso di cambiamento
ON DELETE CASCADE -- viene cancellato il carrello in caso di cancellazione dal sito(profilo)

);

-- DETTAGLIO CARRELLO

CREATE TABLE IF NOT EXISTS dettaglio_carrello(
id_carrello INT NOT NULL,
id_prodotto INT NOT NULL,
quantita INT NOT NULL DEFAULT 1 CHECK(quantita>0),

PRIMARY KEY (id_carrello, id_prodotto), -- imoedisce di aggiungere lo stesso prodotto 2 volte

FOREIGN KEY (id_carrello) REFERENCES carrello(id) -- se si cancella il carrello si cancellano anche i prodotti
ON UPDATE CASCADE 
ON DELETE CASCADE,

FOREIGN KEY (id_prodotto) REFERENCES prodotto(id) -- Garantisce che l'utente metta nel carrello solo articoli realmente esistenti nel catalogo del sito.
ON UPDATE CASCADE
ON DELETE RESTRICT -- se un amministratore prova a cancellare un prodotto mentre che è in un carrello il database blocca l'operazione


);


-- RECENSIONE

CREATE TABLE IF NOT EXISTS recensione (
    id INT AUTO_INCREMENT PRIMARY KEY UNIQUE,
    voto TINYINT NOT NULL CHECK (voto BETWEEN 1 AND 5), -- Il backend invia il voto, il DB controlla che sia da 1 a 5
    commento TEXT,
    data_recensione TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    id_utente INT NOT NULL,
    id_prodotto INT NOT NULL,
    
    -- Un utente può lasciare solo UNA recensione per ogni prodotto, per sicurezza e per evitare che bot recensiscano all'infinito
    UNIQUE (id_utente, id_prodotto),
    
    FOREIGN KEY (id_utente) REFERENCES utente(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (id_prodotto) REFERENCES prodotto(id) ON UPDATE CASCADE ON DELETE CASCADE
);

-- INDIRIZZO SPEDIZIONE

CREATE TABLE IF NOT EXISTS indirizzo_spedizione (
    id INT AUTO_INCREMENT PRIMARY KEY UNIQUE,
    nome_indirizzo VARCHAR(50) NOT NULL, -- Es. "Casa", "Lavoro"
    via VARCHAR(255) NOT NULL,
    citta VARCHAR(100) NOT NULL,
    cap VARCHAR(10) NOT NULL,
    provincia VARCHAR(5) NOT NULL,
    
    id_utente INT NOT NULL,
    FOREIGN KEY (id_utente) REFERENCES utente(id) ON UPDATE CASCADE ON DELETE CASCADE
);

-- METODO PAGAMENTO

CREATE TABLE IF NOT EXISTS metodo_pagamento (
    id INT AUTO_INCREMENT PRIMARY KEY UNIQUE,
    tipo_pagamento VARCHAR(50) NOT NULL, -- Es. "Carta di Credito", "PayPal"
    fornitore VARCHAR(50) NOT NULL, -- Es. "Visa", "Mastercard"
    ultime_cifre VARCHAR(4) NOT NULL, -- Mostra solo le ultime 4 cifre (es. **** 4321) per sicurezza
    token_pagamento VARCHAR(255) NOT NULL, -- Il codice sicuro generato dal backend/Stripe
    
    id_utente INT NOT NULL,
    FOREIGN KEY (id_utente) REFERENCES utente(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS codice_sconto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codice VARCHAR(20) NOT NULL UNIQUE, -- es: "ESTATE20"
    
    -- Può essere uno sconto in percentuale o un importo fisso (es. 5€)
    percentuale_sconto DECIMAL(5,2) DEFAULT 0 CHECK(percentuale_sconto BETWEEN 0 AND 100),
    importo_sconto DECIMAL(10,2) DEFAULT 0 CHECK(importo_sconto >= 0),
    
     -- Validità del coupon
    data_inizio DATE NOT NULL,
    data_fine DATE NOT NULL,
    attivo BOOLEAN DEFAULT TRUE -- 1 se è ancora valido, 0 se è stato disattivato
);

-- ORDINE
CREATE TABLE IF NOT EXISTS ordine (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data_ordine TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    totale_ordine DECIMAL(10, 2) NOT NULL CHECK (totale_ordine >= 0), 
    stato_ordine TINYINT NOT NULL DEFAULT 1 CHECK (stato_ordine IN (1, 2, 3, 4)), 
    
    -- QUI CI SONO I CAMPI DELLO SCONTO
    id_codice_sconto INT NULL, 
    sconto_applicato DECIMAL(10, 2) DEFAULT 0, 
    
    -- QUI CI SONO I COLLEGAMENTI FONDAMENTALI CHE MANCAVANO
    id_utente INT NOT NULL,
    id_indirizzo INT NOT NULL,              -- ECCOLO!
    id_metodo_pagamento INT NOT NULL,       -- ECCOLO!
    
    -- E QUI SOTTO CI SONO LE FOREIGN KEY PER CREARE LE LINEE NEL DIAGRAMMA
    FOREIGN KEY (id_utente) REFERENCES utente(id) ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (id_indirizzo) REFERENCES indirizzo_spedizione(id) ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (id_metodo_pagamento) REFERENCES metodo_pagamento(id) ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (id_codice_sconto) REFERENCES codice_sconto(id) ON UPDATE CASCADE ON DELETE SET NULL
);

-- DETTAGLIO ORDINE

CREATE TABLE IF NOT EXISTS dettaglio_ordine (
    id_ordine INT NOT NULL,
    id_prodotto INT NOT NULL,
    quantita INT NOT NULL CHECK (quantita > 0), -- impedisce l'acquisto di 0 o meno pezzi
    
    -- Il prezzo storico del prodotto al momento dell'acquisto
    prezzo_storico DECIMAL(10, 2) NOT NULL CHECK (prezzo_storico >= 0), -- max 10 cifre e 2 dopo la virgola
    iva_storica DECIMAL(4,2) NOT NULL,
    -- Chiave primaria composta: lo stesso prodotto compare una sola volta nello stesso ordine
    PRIMARY KEY (id_ordine, id_prodotto),
    
    -- Legame con l'ordine
    FOREIGN KEY (id_ordine) REFERENCES ordine(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE, -- Se si cancella l'ordine tipo per reset, si cancellano i suoi dettagli
        
    -- Legame con il prodotto
    FOREIGN KEY (id_prodotto) REFERENCES prodotto(id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT -- Impedisce di cancellare un prodotto se compare in un ordine passato
);