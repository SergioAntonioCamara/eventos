import React, { useState } from 'react';
import axios from 'axios';

const Register = () => {
  const [user, setUser] = useState({
    name: '',       
    email: '',
    password: '',
    rol: 'USER'    // Valor por defecto para el rol
  });
  const [error, setError] = useState(null);

  const handleChange = (e) => {
    setUser({
      ...user,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      // Enviar datos de registro al backend
      const response = await axios.post('http://localhost:8080/api/customers/register', user); 
      console.log('Usuario registrado:', response.data);
      // Limpiar el formulario o redirigir al usuario si es necesario
      setUser({ name: '', email: '', password: '', rol: 'USER' });
    } catch (err) {
      setError('Error al registrar el usuario');
      console.error(err);
    }
  };

  return (
    <div className="register-page">
      <h2>Registro de Usuario</h2>
      {error && <p className="error">{error}</p>}
      <form onSubmit={handleSubmit}>
        <div>
          <label>Nombre</label>
          <input
            type="text"
            name="name"
            value={user.name}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Email</label>
          <input
            type="email"
            name="email"
            value={user.email}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Contraseña</label>
          <input
            type="password"
            name="password"
            value={user.password}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Rol</label>
          <select
            name="rol"
            value={user.rol}
            onChange={handleChange}
            required
          >
            <option value="USER">Usuario</option>
            <option value="ORGANIZER">Organizador</option>
          </select>
        </div>
        <button type="submit">Registrar</button>
      </form>
    </div>
  );
};

export default Register;
