package com.example.gbfindadmin;

import android.util.Log;

import com.google.auth.oauth2.GoogleCredentials;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;

public class AccessToken {

    private static final String firebaseMessagingScope = "https://www.googleapis.com/auth/firebase.messaging";

    public String                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       getAccessToken() {


        try {
            String jsonString = "{\n" +
                    "  \"type\": \"service_account\",\n" +
                    "  \"project_id\": \"orderapp-52aed\",\n" +
                    "  \"private_key_id\": \"3a634b9f2d3c592e51270d2872970da2adaad44c\",\n" +
                    "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDSV8T12Hd8HIHX\\nj2hUBx4X2hC7juyuq5NpgrDhm6C2LveXbT4Km05VmxL62uwXyjWMUfxrthLr/KHA\\n0AD95YOabJiXp99+ynRF6RMRlbZGrS3CRDBj+p3Mtr+/YWzpxNvlk3vn8ZyO87tD\\nfQ1EZwqb+3b5VzoHVuwihVlwWR/Q2N+kKJ7oaYxkvc7SogekmlizhVpTvuhLXxTB\\n5/buwf0MUE5M963G/pBgyl2+n1d/mN6XgzoiYaX47czuYQqYCVpg2md2vbmTBIyG\\nHtuOaoAg+qW06c0Ux6pahz6/PMm9PWpVnU/SEMaCGYIUuTAMn4GhDtTmVtkm2rHp\\n2dcUaW7lAgMBAAECggEATLekZ8J2+fZvyQ5OD++0oyBu3+mmG6MKlf4Lw41+lVQW\\nC83VRgsVDBYOWx5D4/1lgHNarHJw/k9bM7bp1bDDSix3j0C0PEtdEy6KPOys2aRK\\nP0OdnqmGqmaxEv1WuwCI73fWRmeSIXV2BIx0rVEjxnZ2E0UkLg8e+wxF7bsj5oi5\\nWYaOGC6fxMvwiQ13eJHJgsJOA76yrpS5QGc0wCrrA5wUI0uASGwcb4FlA/ohEegZ\\nttkw9wMZlvLHkZH+oZKmdhWOHNHU4OdKmwWOpRjx3dKtHIyYq6hLsio75hvp3EXg\\nqfxXOdiXjWa71xYVQg7lTnYmyi63e2M0xHfvQGC/FQKBgQD73wGWsM5aqeHjyWT7\\nY5T0Iom7n7lwzzcl7qZbG8FlNpV2loEZHpTV78Px3mLvFXA3X98HDg0pCRIawmaT\\n1OPDif+cnJHjKAZ28n/bQVXQNPXTEcgOOgDmo5ev1llYDjSLFBowij5a2AZ5rVrQ\\nV5qV9nHgv0sqedtmKs0Xp0LqIwKBgQDVynyvBE7+VA6uAsfErdIaHZiIrBwaXqKQ\\nTD3hRjZ7z97aKUyXa/5yvhMFfBto9ePFVZOk2Vw/DegHP3bmlC5A0d0XydxPBorD\\nyAirTMZNKv+N4H4FJunxYzo/rz0hKhAMZlq407/bH3aMECtF4kcgJvCmOSwSpC+x\\nR7ZElHH/VwKBgBolLRIKanFI3zT5C+14idbJ2whJ7UMDj5L1cflVKT8ralp0BxSn\\ncwW+LNXnotd0n0BwSEOANueClImIkoNX/wimQ41rR02QMxOX2qPU5WaGiNoPJ3gq\\nlztZ0f4jvwR//P5O6OUPBKgDcrCgySOFZHuaqHmPAet32MmDOeTxrOWHAoGAJNHd\\nzWqpjCW/7XovG5FwKO1F8uvpXyVDTCTFV4H3KjWvxNTuCvofdXWE4yHvupD2rGrD\\npdWFjJqBhpYIjUeh+6npMQqj9bxDs9pEm/P6BxpLrwylx49fJSKYypUMqySKZQhP\\nwZs+ZKezjAXQ3hVNeJ0cPxSDoaZDTH30RzVluLsCgYBPyzgiEujMBGDoJwG2WsMK\\n9oPGHG+z5ldnBsV7GiNTJlfm7wulnCcTJM9vMWm+6fVzkyiNF+/JsSBnwqqnEBU/\\nCp2QUkau9Pw90tS/EQuRomeYWZa5Dm6yBvrkdChj7ck8TxTx9eIDv4b0gAQ/qyYw\\nHF9nJ/zoRP2sS00nwEoEXg==\\n-----END PRIVATE KEY-----\\n\",\n" +
                    "  \"client_email\": \"firebase-adminsdk-z34d3@orderapp-52aed.iam.gserviceaccount.com\",\n" +
                    "  \"client_id\": \"117646898094430348291\",\n" +
                    "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
                    "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
                    "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
                    "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-z34d3@orderapp-52aed.iam.gserviceaccount.com\",\n" +
                    "  \"universe_domain\": \"googleapis.com\"\n" +
                    "}";


//            String jsonString = "{\n" +
//                    "  \"type\": \"service_account\",\n" +
//                    "  \"project_id\": \"ridingapp-186e2\",\n" +
//                    "  \"private_key_id\": \"3725eb038e7860fd8ac3ee2e0f979ab286cc22c5\",\n" +
//                    "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCcvUCE/G8fHgpt\\nEdrW3QDXD9kabU8vi88roaxP7awhBync1kQrJSrhDZJi9FwLZIce2iEdhE6jbPo0\\nUqd4TqHr/l+t4ogx1HFifBEFv8AGQ13maVaa39Ww7vzPQAwVu/iFiEqeVAX4bKty\\nZFvhPz+EagseLbYh1N0ZNZQgv0NfRqmXz7ssbr5W7kDJDEJ+3XiCt56MNGO3ygjt\\nPjlXp+DkwHrNjRE50STHNrIs5zAIEvUnyvJJDN3Iu6RSCEZ7JjThqEEIqy+2KiyM\\nZmoAKxz6o7f6/xPRG3MOwRx22/X4VlOEkstL/dhcz0gosC+llIwEo4BNgu+4P082\\nI2zx7supAgMBAAECggEAEkWdt+MVsTL3VC+4q0OoTWnzjTkg+hH6XMPEKq0yW8dS\\nAgSYfc8faI0Ee7Sw5kMZ3WbgZWh/Il4Z7O+VxlikV7aXwNWfuu9hKHH+KgVohVJl\\nJ+8vwW5GoxG5Nw/oWMBJAIga6sowx/0jH3rDm0acSPtzwFjj2p3OCJpOYmLpn7mW\\nP7R1n313RM9b8m/JBZluhATLwKkUwdTOt/Nuv5Rcfl3Lk6pqpB0PbIW/nY3yqLnO\\nf8RTXWInswsrjnYkcvbGayvjYnNvlFQsVCwPCJJbdfnk+es0vjUd5k58qj6Lp9wX\\nCgXPJqml09TC4YJdBX7IqAfyHpyfMxgBSgVil26y4QKBgQDZV9Y9AJU/cS5W86V3\\n1EcBn7g+2HxSjSCSZQDyKM3BTLITjDObLIYsoTZkfHp/fZuzZYv47SO3cTCIG7u+\\nciEUpwFmNU4KPpxu1AbPAu41YucBLswHAopu77x7lnlKSNZpJQLD7bev6QFgpuks\\npW52QPQaNn3HrvYoF2wvEJ6h/wKBgQC4nflQd+A9JVOXNcdySxM+aS+h/6/X+L/2\\nPRwU9E9mAMrJUTt+rd9bi/W12twyU+ZCxz6Z3xOZ+I2R1kaJVqZ0EUr1JsVa0cPe\\nJO0JAgQ1UdyDvOnp6U94nSDxifYs8fzepErUSVagoQUrHeHeAzzuqrwxmRKiA67e\\nrNVg1b5CVwKBgQCM2uEsbblMAWTf002UiE1wXvvANvrzYSUP20euqQUX1kW+Z2l2\\nknduaxheLVISV+xVamU5cS5pj4C9ZQPanAqWYNmGTNuDxioJpX24IZURokRFvvdZ\\nP6tJ1DLaAZ4fp27Ve7f8FI6sAZzz75hEZ/5bwyKv7kq748cCGpPxOJsmLwKBgQCw\\nzjnyopuSc6j2a36zKssnPj1r/B8/yu6suCGov8E6gw+ydaVw0LvURNnwa8XuPQOM\\neJyvaECxeKS8QmYTKXUIO3d6CMOBEttuaBbKRbAaEGgLkmTCq7p8XJ8sM2Ab8zSl\\nVNqFLCdWdl8ox9mEcb1tJP5O0bZSiwxyHTvNHWa0yQKBgQCDG5fROCDPMymZGn4z\\nhwy3zfSvtFG38XO2K/vPt0tQQg7+KBgNwbVo9SawXMMgDHtY+sUmgDfpZBHbftjd\\nMEYLwYL07yVN4J7x71AwcUTcIsGSQf/dNnzhpZipYrYOnj9fv55Ys9aYKd0F3Mfa\\nrvHUGPdUYBDC2Yzo+rROGGUrcg==\\n-----END PRIVATE KEY-----\\n\",\n" +
//                    "  \"client_email\": \"firebase-adminsdk-5wf3n@ridingapp-186e2.iam.gserviceaccount.com\",\n" +
//                    "  \"client_id\": \"115872653292679622908\",\n" +
//                    "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
//                    "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
//                    "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
//                    "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-5wf3n%40ridingapp-186e2.iam.gserviceaccount.com\",\n" +
//                    "  \"universe_domain\": \"googleapis.com\"\n" +
//                    "}";

            InputStream stream = new ByteArrayInputStream(jsonString.getBytes(StandardCharsets.UTF_8));

            GoogleCredentials googleCredentials =
                    GoogleCredentials.fromStream(stream).createScoped((new ArrayList<>(Collections.singleton(firebaseMessagingScope))));

            googleCredentials.refresh();
            return googleCredentials.getAccessToken().getTokenValue();
        } catch (Exception e) {

            Log.d("error" ,e.getMessage());
            return null;
        }


    }
    }





