function fingerLogin() {
    alert("Fingerprint scan started");

    fetch('/api/fingerprint/login?credentialId=demo123', {
        method: 'POST'
    })
        .then(res => res.text())
        .then(token => {
            localStorage.setItem("token", token);
            window.location.href = "/";
        })
        .catch(() => alert("Fingerprint failed"));
}
navigator.credentials.create({
    publicKey: {
        challenge: new Uint8Array(32),
        rp: { name: "TravelSewa" },
        user: {
            id: new Uint8Array(16),
            name: "user@email.com",
            displayName: "User"
        },
        pubKeyCredParams: [{ type: "public-key", alg: -7 }],
        authenticatorSelection: {
            userVerification: "required"
        }
    }
}).then(cred => {


    const credentialId = btoa(
        String.fromCharCode(...new Uint8Array(cred.rawId))
    );


});
