document.addEventListener("DOMContentLoaded", () => {
    createFooter();
    createNavbar();
});

const createFooter = () => {
    let footerElement = document.querySelector("footer");
    const footerContent = `
    <div class="footer-content">
            <p>Contacto</p>
            <ul>
                <li>Teléfono: 55 1234 5678</li>
                <li>Correo: Arias_Daniel.00@outlook.com</li>
            </ul>
            <p class="copyright">&copy; 2025 Net-Shopping Online. Todos los derechos reservados.</p>
    </div>
    
`;
    footerElement.innerHTML = footerContent;
};

function createNavbar() {
    const navbar = `
        <nav class="navbar navbar-expand-lg bg-body-tertiary" data-bs-theme="dark">

            <div class="container-fluid">

                <a class="navbar-brand" href="index.html">Daniel Arias</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="index.html">Inicio</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="cv.html">CV</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="https://github.com/Ros-Dan26">GitHub</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link"
                                href="https://www.linkedin.com/in/daniel-arian-arias-rosales-0a5876364?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app">Linkedint</a>
                        </li>
                    </ul>
                </div>

            </div>
        </nav>
    `;
    document.getElementById('navbar').innerHTML = navbar;
}
