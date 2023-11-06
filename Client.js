const axios = require('axios');

async function callServerEndpoints() {
    try {
        const responseAdd = await axios.get('http://localhost:8080/add?first=24&second=56');
        if (responseAdd.status === 200) {
            console.log('Success:', responseAdd.data);
        } else {
            console.log('Fail:', responseAdd.data);
        }

        const responseSubtract = await axios.get('http://localhost:8080/substract?first=11&second=5');
        if (responseSubtract.status === 200) {
            console.log('Success:', responseSubtract.data);
        } else {
            console.log('Fail:', responseSubtract.data);
        }

    } catch (error) {
        console.error('Error:', error.message);
    }
}

callServerEndpoints();
