import 'package:dio/dio.dart';
import 'secure_storage.dart';
import 'constants.dart';

class AuthService {
  final Dio dio;

  AuthService()
      : dio = Dio(BaseOptions(
    baseUrl: BASE_URL,
    connectTimeout: const Duration(seconds: 10),
    receiveTimeout: const Duration(seconds: 10),
    headers: {
      "Content-Type": "application/json",
    },
  ));

  /// Login and save JWT token
  Future<void> login(String email, String password) async {
    try {
      final response = await dio.post('/api/auth/login', data: {
        'email': email,
        'password': password,
      });

      if (response.statusCode == 200) {
        final token = response.data['token']; // adjust key if needed
        await SecureStorage.write('jwt_token', token);
      } else {
        throw Exception('Login failed with status: ${response.statusCode}');
      }
    } catch (e) {
      throw Exception('Login failed: $e');
    }
  }

  /// Logout: delete token
  Future<void> logout() async {
    await SecureStorage.delete('jwt_token');
  }

  /// Optional: get stored token
  Future<String?> getToken() async {
    return await SecureStorage.read('jwt_token');
  }
}
