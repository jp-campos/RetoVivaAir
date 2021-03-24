Feature: Hacer una reserva en Viva Air
  Como un turista
  Quiero hacer una reserva para un vuelo
  Para poder viajar

  Scenario: Hacer una reserva para mañana entre Bogota - Medellin
    Given Estoy navegando por el internet
    When Reservo el vuelo mas barato entre Bogota y Medellin
    Then El valor del vuelo deberia ser el mismo al final de la transaccion