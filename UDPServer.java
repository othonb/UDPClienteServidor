//package sockets;

import java.io.*;
import java.net.*;

/**
 *
 * @author  Othon Batista
 */
class UDPServer 
{

public static void main(String[] args)
{
    try
    {
        // Instancia e cria um socket UDP servidor 
	    DatagramSocket socketServidor = new DatagramSocket(6789);
	    System.out.println ("Servidor escutando a porta 6789...");

        // Instancia e cria os vetores para receber e enviar dados do/para o cliente
        byte[] dadosRecebidos; 
        byte[] dadosEnviados; 
  
        // Servidores são laços infinitos
        while (true) { 

            // Cria os vetores para receber e enviar dados do/para o cliente
            dadosRecebidos = new byte [1024];
            dadosEnviados = new byte [1024];

            // Cria um datagrama UDP para receber os dados do cliente
            DatagramPacket datagramaRecebido = new DatagramPacket(dadosRecebidos, dadosRecebidos.length); 
  
            // Recebe um datagrama UDP através do socket
            socketServidor.receive(datagramaRecebido); 

            // Transforma os dados do datagrama (byte []) em String
            String frase = new String(datagramaRecebido.getData()); 
  
            // Recupera o endereço IP do datagrama UDP recebido para responder ao cliente
            InetAddress enderecoIp = datagramaRecebido.getAddress(); 
  
            // Recupera a porta do datagrama UDP recebido para responder ao cliente
            int porta = datagramaRecebido.getPort(); 

            // Passa a String para maiúsculo
            String fraseMaiuscula = frase.toUpperCase(); 

            // Recupera o vetor de bytes da String
            dadosEnviados = fraseMaiuscula.getBytes(); 

            // Monta um datagrama UDP para responder ao cliente
            DatagramPacket datagramaEnviado = new DatagramPacket(dadosEnviados, 
                                                                dadosEnviados.length, 
                                                                enderecoIp, 
                                                                porta); 

            // Envia o datagrama UDP através do socket ao cliente
            socketServidor.send(datagramaEnviado); 
        }
    }
    catch (IOException e)
    {
        System.err.println("Nao foi possivel abrir a porta 6789! Deve existir um servidor rodando...");
    }
}

}

