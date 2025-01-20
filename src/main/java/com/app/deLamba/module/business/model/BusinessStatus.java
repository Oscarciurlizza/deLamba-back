package com.app.deLamba.module.business.model;

public enum BusinessStatus {
  PENDING, // Recién creado, pendiente de revisión
  ACTIVE, // Negocio activo y operando
  SUSPENDED, // Temporalmente suspendido
  CLOSED // Cerrado permanentemente
}