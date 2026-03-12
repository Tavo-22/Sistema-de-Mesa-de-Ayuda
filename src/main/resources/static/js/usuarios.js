const API = "http://localhost:8080/usuarios";

function crearUsuario(){

    const nombre = document.getElementById("nombre").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    const usuario = {
        nombre: nombre,
        email: email,
        password: password
    };

    fetch(API, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(usuario)
    })
    .then(response => response.json())
    .then(data => {
        alert("Usuario creado correctamente");
        cargarUsuarios();
    });
}

function cargarUsuarios(){

    fetch(API)
    .then(response => response.json())
    .then(data => {

        const lista = document.getElementById("listaUsuarios");
        lista.innerHTML = "";

        data.forEach(usuario => {

            const li = document.createElement("li");

            li.textContent = usuario.nombre + " - " + usuario.email;

            lista.appendChild(li);
        });

    });

}