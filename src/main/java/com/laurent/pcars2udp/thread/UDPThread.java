package com.laurent.pcars2udp.thread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.laurent.pcars2udp.adapter.ParticipantInfoAdapter;
import com.laurent.pcars2udp.adapter.TelemetryDataAdapter;
import com.laurent.pcars2udp.dto.ParticipantInfo;
import com.laurent.pcars2udp.dto.TelemetryData;

@Component
public class UDPThread {

	private final static int port = 5606;

	@Autowired
	private ParticipantInfoAdapter participantInfoAdapter;

	@Autowired
	private TelemetryDataAdapter telemetryDataAdapter;

	@Autowired
	private ParticipantInfo participantInfo;

	@Autowired
	private TelemetryData telemetryData;

	@Async
	public void run() {
		try {

			// Création de la connexion côté serveur, en spécifiant un port d'écoute
			DatagramSocket server = new DatagramSocket(port);

			// On s'occupe maintenant de l'objet paquet
			byte[] buffer = new byte[8192];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

			boolean readSocket = true;

			while (readSocket) {

				// Cette méthode permet de récupérer le datagramme envoyé par le client
				// Elle bloque le thread jusqu'à ce que celui-ci ait reçu quelque chose.
				server.receive(packet);

				if (packet.getLength() == TelemetryData.LENGTH_BYTES_BUFFER) {
					// Float vitesse = getFloat(packet.getData(), 120);
					// vitesse = vitesse * 60 * 60 / 1000;
					// print("\nvitesse : " + vitesse);

					telemetryData = telemetryDataAdapter.getTelemetryData(telemetryData, packet.getData());

				}
				// charset
				if (packet.getLength() == ParticipantInfo.LENGTH_BYTES_BUFFER) {
					participantInfo = participantInfoAdapter.getParticipantInfo(participantInfo, packet.getData());

				}

				// On réinitialise la taille du datagramme, pour les futures réceptions
				packet.setLength(buffer.length);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
