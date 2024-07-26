import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
  stages: [
    { duration: '30s', target: 10 }, // Ramp-up to 10 users over 30 seconds
    { duration: '1m', target: 10 }, // Stay at 10 users for 1 minute
    { duration: '10s', target: 0 }, // Ramp-down to 0 users over 10 seconds
  ],
};

export default function () {
  let res = http.get('http://localhost:5000'); // Substitua pela URL da sua aplicação Flask
  check(res, {
    'status is 200': (r) => r.status === 200,
  });
  sleep(1);
}
