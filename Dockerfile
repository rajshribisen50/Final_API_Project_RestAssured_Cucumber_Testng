# Use existing Maven + JDK 17 image (this tag exists)
FROM maven:3.9.12-eclipse-temurin-17

# Set working directory
WORKDIR /app

# Install essential tools safely
RUN apt-get update -o Acquire::Check-Valid-Until=false -o Acquire::Check-Date=false && \
    apt-get install -y --no-install-recommends wget unzip git && \
    rm -rf /var/lib/apt/lists/*

# Install Allure CLI
RUN wget -O /tmp/allure.zip https://github.com/allure-framework/allure2/releases/download/2.22.1/allure-2.22.1.zip && \
    unzip /tmp/allure.zip -d /opt/allure && \
    rm /tmp/allure.zip

# Add Allure to PATH
ENV PATH="/opt/allure/allure-2.22.1/bin:${PATH}"

# Copy project files
COPY . .

# Default command: clean and run tests
CMD ["mvn", "clean", "test"]
