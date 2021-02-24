//#include <QtNetwork> // QT Network variables
#include <ctime> // random number thing which I'm supposed to understand but needed anyways, oh well
#include <algorithm> // A thing to sacrifice bandwidth for latency (less PING requests)
#include "server.h" // Servers header
#include "player.h" // Player/User handler
#include "serverconfig.h" // Internal configuration
#include "scriptengine.h" // Script engine so we can run JavaScript along with the server (Now people who can do JS have a job!)
#include "sql.h" // Oh look SQL headers its just so damn fun!
#include "analyze.h" // Stops spammers from self DOS'ing the server (Won't stop DDoS attacks...Just the spammers...)
#include "networkutilities.h" // More helpful stuff I'm supposed to understand
#include "registrycommunicator.h" // A registry so we can have a server list and such.
#include "pluginmanager.h" // Needed for a few things but gets deleted internally anyways (don't ask...)

using namespace std;

Server *Server::serverIns = NULL;

// Port things.
Server::Server(quint16 port) : registry(nullptr), serverPorts(), showLogMessages(true),
    lastDataId(0), playercounter(0),
    numberOfPlayersLoggedIn(0), myengine(nullptr)
{
    serverPorts << port;
}

Server::Server(QList<quint16> ports) : registry(nullptr), serverPorts(), showLogMessages(true),
    lastDataId(0), playercounter(0),
    numberOfPlayersLoggedIn(0), myengine(nullptr)
{
    foreach(quint16 port, ports)
        serverPorts << port;
}

Server::~Server()
{
#ifndef BOOST_SOCKETS
    foreach (QTcpServer* myserver, myservers)
        myserver->deleteLater();
#endif
    // Told you it just gets deleted.
    delete pluginManager;
}

extern bool skipChecksOnStartUp;

// Oh no here it begins
void Server::start(){
    serverIns = this;

#ifndef QT5
    QTextCodec::setCodecForCStrings(QTextCodec::codecForName("UTF-8"));
    QTextCodec::setCodecForTr(QTextCodec::codecForName("UTF-8"));
#endif

#ifndef BOOST_SOCKETS
    for (int i = 0; i < serverPorts.size(); ++i) {
        myservers.append(new QTcpServer());
    }
#else
    for (int i = 0; i < serverPorts.size(); ++i) {
        myservers.append(manager.createServerSocket());
    }
#endif
    srand(time(NULL));

#ifdef Q_OS_WIN
    // Do nothing for now unless you want to ban windows from hosting a server (like that will happen anytime soon)
#endif
    }

    // Make settings.
    QSettings s("config", QSettings::IniFormat);

    auto setDefaultValue = [&s](const char* key, const QVariant &defaultValue) {
        if (!s.contains(key)) {
            s.setValue(key, defaultValue);
        }
    };

    // Hopefully I won't get caught by the WoW R.I.O.T Police Force for this edit to server.cpp
    setDefaultValue("Scripts/SafeMode", false); // All this does is useless crap like stopping you from giving someone owner, or a shutdown command etc.
    setDefaultValue("Server/Password", "123456"); // Best password for server default ik.
    setDefaultValue("Server/RequirePassword", false); // False because we dont want to use a password rn
    setDefaultValue("Server/Private", false); // Its a public server, So this setting is false
    setDefaultValue("Server/Name", QString()); // Pick a server name from GUI
    setDefaultValue("Server/Announcement", QString()); // An announcement which will show when someone logs in
    setDefaultValue("Server/MaxPlayers", 0); // 0 = Infinite Slots (Can be changed in GUI

    // MMO Settings itself.
    setDefaultValue("Server/DefaultSpawn", ""); // For when levels are done can change where people spawn in etc.
    setDefaultValue("Server/XPMultiplier", 1); // Default XP Multiplier

    setDefaultValue("Network/ProxyServers",QString("127.0.0.1,::1%0,localhost")); // Localhost isn't a proxy but messes up internal engine for being a bad IP range...
    setDefaultValue("Network/LowTCPDelay", false); // Sacrifice bandwidth for latency?
    setDefaultValue("AntiDOS/ShowOveractiveMessages", true); // Shows the IPs which spam in chat to all
    setDefaultValue("AntiDOS/TrustedIps", "127.0.0.1,::1%0,localhost"); // These IPs cannot be banned, obv localhost.
    setDefaultValue("AntiDOS/MaxPeoplePerIp", 1); // Since when do we allow TWO people per HOUSE???
    setDefaultValue("AntiDOS/MaxCommandsPerUser", 25); // Doubt someone would spam 50 commands per minute
    setDefaultValue("AntiDOS/MaxKBPerUser", 25); // You go over this limit per minute you get kicked out
    setDefaultValue("AntiDOS/MaxConnectionRatePerIP", 6); // Not a clue but needed anyways..
    setDefaultValue("AntiDOS/NumberOfInfractionsBeforeBan", 10); // Bans someone if they spam 10 times over 25 KB per minute
    setDefaultValue("AntiDOS/Disabled", false); // As if I'd disable such a system?
    setDefaultValue("Registry/IP", "some-domain-or-ip-goes.here"); // Some registry for servers to use or something (OVH recommended)

    // Oh no...I feel the pain...SQL is getting closer!
    setDefaultValue("SQL/Driver", SQLCreator::SQLite);
    setDefaultValue("SQL/Database", "default"); // Blah blah blah database name blah blah
    setDefaultValue("SQL/Port", 5432);
    setDefaultValue("SQL/User", "postgres");
    setDefaultValue("SQL/Pass", "default");
    setDefaultValue("SQL/Host", "localhost"); // Keep database local else people would hack it.
    setDefaultValue("SQL/DatabaseSchema", ""); // For different types of databases

    registry = new RegistryCommunicator(s.value("Registry/IP").toString(), this); // Needs domain/IP

    if (isSql()) {
        try {
            SQLCreator::createSQLConnection();
        } catch (const QString &ex) {
            forcePrint(ex);
        }
    }

    // Im done SQL hurts my brain will continue soon
