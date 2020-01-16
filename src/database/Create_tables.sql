CREATE TABLE IF NOT EXISTS User(
    id_utilisateur VARCHAR(4) PRIMARY KEY,
    login_utlisateur VARCHAR(20),
    user_name VARCHAR(20),
    IP_adress VARCHAR(15),
    MAC_address VARCHAR(17)
);

CREATE TABLE IF NOT EXISTS MESSAGE(
	Sender VARCHAR(20),
	Receiver VARCHAR(20),
	Text VARCHAR(1000),
	Time VARCHAR(8)
);	