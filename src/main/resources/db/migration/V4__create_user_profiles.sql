CREATE TABLE IF NOT EXISTS user_profiles (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id VARCHAR(50) NOT NULL UNIQUE,
    department_id INTEGER NOT NULL,
    workplace_id INTEGER NOT NULL,
    phone_number VARCHAR(20),
    email VARCHAR(255) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_profiles_account FOREIGN KEY (user_id) REFERENCES user_accounts (user_id) ON DELETE CASCADE,
    CONSTRAINT fk_profiles_department FOREIGN KEY (department_id) REFERENCES departments (department_id),
    CONSTRAINT fk_profiles_workplace FOREIGN KEY (workplace_id) REFERENCES workplaces (workplace_id)
);
